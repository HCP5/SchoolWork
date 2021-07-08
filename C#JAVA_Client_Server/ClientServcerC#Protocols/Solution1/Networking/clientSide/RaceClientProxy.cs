using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using ConsoleApp1.domain;
using Domain;
using Networking.dtos;
using Networking.protocols;
using Server;
using Service;

namespace Networking.clientSide
{
    public class RaceClientProxy : IMasterService
    {
        private string _host;
        private int _port;

        private IRaceObserver client;
        
        private NetworkStream _stream;

        private IFormatter _formatter;
        private TcpClient _connection;

        private Queue<Response> _responses;

        private volatile bool _finished;

        private EventWaitHandle _waitHandle;

        public RaceClientProxy(string host, int port)
        {
            _host = host;
            _port = port;
            _responses = new Queue<Response>();
        }

        public List<Cursa> GetAllRaces()
        {
            throw new System.NotImplementedException();
        }

        public List<Echipa> GetAllTeams()
        {
            throw new System.NotImplementedException();
        }

        public List<Masina> GetAllCars()
        {
            throw new System.NotImplementedException();
        }

        public void AddCar(Masina masina)
        {
            throw new System.NotImplementedException();
        }

        public List<ParticipantiCursa> GetAllParticipanti()
        {
            throw new System.NotImplementedException();
        }

        public void AddParticipant()
        {
            throw new System.NotImplementedException();
        }

        public List<Sofer> GetAllSoferi()
        {
            throw new System.NotImplementedException();
        }

        public void AddSofer(Sofer sofer)
        {
            throw new System.NotImplementedException();
        }

        public List<Cursa> GetAllUsers()
        {
            throw new System.NotImplementedException();
        }

        public void login(Utilizator user, IRaceObserver raceController)
        {
            Console.WriteLine("Initialize connection, please wait!");
            initializeConnection();
            Console.WriteLine("Connection established!");
            Console.WriteLine("Sending Log in request: "+ user.NumeUtilizator+" "+ user.Passwd);
            sendRequest(new LoginRequest(user));
            Response response = readResponse();
            Console.WriteLine("Given response: " + response.GetType());
            if (response is LogedInResponse)
            {
                this.client = raceController;
                return;
            }
            else
            {
                Console.WriteLine("Close connection...");
                closeConnection();
                throw new Exception("Utilizator sau parola invalida!");
            }
        }

        public void logOut(IRaceObserver observer)
        {
            Console.WriteLine("Logging out");
            sendRequest(new LogOutRequest());
            Response response = readResponse();
            if(response is LogedOutResponse)
            {
                this.client = null;
                closeConnection();
            }
        }

        public object getAllForStage()
        {
            sendRequest(new GetAllRequest());
            Response response = readResponse();
            if (response is TakeAllResponese)
            {
                TakeAllResponese myRespone = (TakeAllResponese) response;
                AllForStageDTO data = myRespone.AllEntities;
                return  data;
            }
            else
            {
                Console.WriteLine("Error reading...");
            }

            return null;
        }

        public List<Sofer> getParticipanti(int idCursa)
        {
            sendRequest(new GetRaceDetailsRequest(idCursa));
            Response response = readResponse();
            if (response is DetailsOfRace)
            {
                DetailsOfRace detailsOfRace = response as DetailsOfRace;
                return detailsOfRace.List;
            }
            return null;
        }

        public List<Sofer> getTeam(string s)
        {
            sendRequest(new GetTeamDetailsRequest(s));
            Response response = readResponse();
            if (response is TeamDetails)
            {
                TeamDetails res = response as TeamDetails;
                return res.List;
            }

            return null;
        }

        public void inscrie(string nume, string nrMasina, string echipa, int idCursa)
        {
            sendRequest(new InscrieRequest(nume,nrMasina,echipa,idCursa));
        }


        private void closeConnection()
        {
            _finished=true;
            try
            {
                _stream.Close();
                //output.close();
                _connection.Close();
                _waitHandle.Close();
                client=null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

        }
        
        private void sendRequest(Request request)
        {
            try
            {
                _formatter.Serialize(_stream, request);
                _stream.Flush();
            }
            catch (Exception e)
            {
                throw new Exception("Error sending object "+e);
            }

        }
        
        private Response readResponse()
        {
            Response response =null;
            try
            {
                _waitHandle.WaitOne();
                lock (_responses)
                {
                    // Monitor.Wait(responses); 
                    response = _responses.Dequeue();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return response;
        }
        
        private void initializeConnection()
        {
            try
            {
                _connection=new TcpClient(_host,_port);
                _stream=_connection.GetStream();
                _formatter = new BinaryFormatter();
                _finished=false;
                _waitHandle = new AutoResetEvent(false);
                startReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }
        private void startReader()
        {
            Thread tw =new Thread(run);
            tw.Start();
        }
        
        public virtual void run()
        {
            while(!_finished)
            {
                try
                {
                    object response = _formatter.Deserialize(_stream);
                    Console.WriteLine("response received "+response);
                    if (response is UpdateResponse)
                    {
                        handleUpdate((UpdateResponse)response);
                    }
                    else
                    {
							
                        lock (_responses)
                        {
                            _responses.Enqueue((Response)response);
                        }
                        _waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error "+e);
                }
            }
        }
        private void handleUpdate(UpdateResponse update)
        {
           client.update();
        }
    }
}