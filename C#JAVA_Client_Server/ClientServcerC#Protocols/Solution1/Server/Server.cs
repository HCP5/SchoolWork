using System;
using System.Net.Sockets;
using System.Threading;
using ConsoleApp1.domain;
using ConsoleApp1.domain.validators;
using Domain;
using Domain.validators;
using Repository;
using Networking.serverSide;

namespace Server
{
    public static class Server
    {
        public static void Main(string[] args)
        {
            IObjectRepo<Utilizator, int> utilizatorRepo=new UtilizatorDBRepository(new UtilizatorValidator());
            IObjectRepo<Sofer, int> soferRepo=new SoferDBRepository(new SoferValidator()); 
            IObjectRepo<Masina, int> masinaRepo=new MasinaDBRepository(new MasinaValidator());
            IObjectRepo<Echipa, int> echipaRepo=new EchipaDBRepository(new EchipaValidator());
            IObjectRepo<ParticipantiCursa, int> participantiRepo=new ParticipantiDBRepository(new ParticipantiValidator());
            IObjectRepo<Cursa, int> cursaRepo=new CursaDBRepository(new CursaValidator());

            IMasterService service = new MasterServices(utilizatorRepo, soferRepo, masinaRepo, echipaRepo, participantiRepo, cursaRepo);

            SerialChatServer server = new SerialChatServer("127.0.0.1", 55555, service);
            server.Start();
        }
    }

    public class SerialChatServer : ConcurrentServer
    {
        private IMasterService server;
        private RaceClientWorker worker;
        public SerialChatServer(string host, int port, IMasterService server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("SerialChatServer...");
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new RaceClientWorker(server, client);
            return new Thread(new ThreadStart(worker.Run));
        }
    }
}