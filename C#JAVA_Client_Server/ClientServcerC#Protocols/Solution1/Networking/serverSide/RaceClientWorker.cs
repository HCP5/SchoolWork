using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Remoting.Messaging;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using ConsoleApp1.domain;
using Domain;
using Networking.dtos;
using Networking.protocols;
using Server;
using Service;

namespace Networking.serverSide
{
    public class RaceClientWorker : IRaceObserver //va implementa observer-ul
    {
        private IMasterService _server;
        private TcpClient _connection;

        private NetworkStream _stream;
        private IFormatter _formatter;
        private volatile bool _connected;

        public RaceClientWorker(IMasterService server, TcpClient connection)
        {
            _server = server;
            _connection = connection;
            try
            {
                _stream = connection.GetStream();
                _formatter = new BinaryFormatter();
                _connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
        }

        public virtual void Run()
        {
            while(_connected)
            {
                try
                {
                    object request = _formatter.Deserialize(_stream);
                    object response =handleRequest((Request)request);
                    if (response!=null)
                    {
                        sendResponse((Response) response);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
				
                try
                {
                    Thread.Sleep(1000);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }
            try
            {
                _stream.Close();
                _connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error "+e);
            }
        }

        private void sendResponse(Response response)
        {
            Console.WriteLine("sending response "+response);
            _formatter.Serialize(_stream, response);
            _stream.Flush();
        }

        private object handleRequest(Request request)//se implementeaza cand un client face cerere
        {
            Response response = null;
            if (request is LoginRequest)
            {
                Console.WriteLine("Received request: "+request.GetType());
                LoginRequest loginRequest = (LoginRequest) request;
                Utilizator user = loginRequest.User;
                try
                {
                    lock (_server)
                    {
                        Console.WriteLine("Trying to connect...");
                        _server.login(user, this);
                        Console.WriteLine("Connected!");
                    }

                    return new LogedInResponse();
                }
                catch (Exception e)
                {
                    Console.WriteLine("Failed to connect...\n Closing connection...");
                    _connected = false;
                    return new NoUserFound();
                }
            }

            if (request is LogOutRequest)
            {
                Console.WriteLine("Logging out");
                LogOutRequest logOutRequest = (LogOutRequest) request;
                lock (_server)
                {
                    _server.logOut(this);
                }

                _connected = false;
                return new LogedOutResponse();
            }

            if (request is GetAllRequest)
            {
                lock (_server)
                {
                    AllForStageDTO allData = (AllForStageDTO) _server.getAllForStage();
                    Response forSend = new TakeAllResponese(allData);
                    sendResponse(forSend);
                }
            }

            if (request is GetRaceDetailsRequest)
            {
                lock (_server)
                {
                    GetRaceDetailsRequest req = (GetRaceDetailsRequest) request;
                    List<Sofer> soferiParticipanti = _server.getParticipanti(req.IdCursa);
                    Response forSend = new DetailsOfRace(soferiParticipanti);
                    sendResponse(forSend);
                }
            }

            if (request is GetTeamDetailsRequest)
            {
                lock (_server)
                {
                    GetTeamDetailsRequest req = (GetTeamDetailsRequest) request;
                    List<Sofer> soferi = _server.getTeam(req.NumeEchipa);
                    Response rez = new TeamDetails(soferi);
                    sendResponse(rez);
                }
            }

            if (request is InscrieRequest)
            {
                lock (_server)
                {
                    InscrieRequest req = request as InscrieRequest;
                    _server.inscrie(req.Nume,req.NrMasina,req.Echipa,req.IdCursa);
                }
            }
            return response;
        }

        public void notifyAll()
        {
            throw new NotImplementedException();
        }

        public void update()
        {
            sendResponse(new UpdateResponse());
        }
    }
}