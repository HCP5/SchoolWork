using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ConsoleApp1.domain;
using Domain;
using Networking.dtos;
using Repository;
using Service;

namespace Server
{
    public class MasterServices : IMasterService
    {
        private IObjectRepo<Utilizator, int> _utilizatorRepo;
        private IObjectRepo<Sofer, int> _soferRepo;
        private IObjectRepo<Masina, int> _masinaRepo;
        private IObjectRepo<Echipa, int> _echipaRepo;
        private IObjectRepo<ParticipantiCursa, int> _participantiRepo;
        private IObjectRepo<Cursa, int> _cursaRepo;

        private List<IRaceObserver> listaObeserveri=new List<IRaceObserver>();
        public MasterServices(IObjectRepo<Utilizator, int> utilizatorRepo, IObjectRepo<Sofer, int> soferRepo, IObjectRepo<Masina, int> masinaRepo, IObjectRepo<Echipa, int> echipaRepo, IObjectRepo<ParticipantiCursa, int> participantiRepo, IObjectRepo<Cursa, int> cursaRepo)
        {
            _utilizatorRepo = utilizatorRepo;
            _soferRepo = soferRepo;
            _masinaRepo = masinaRepo;
            _echipaRepo = echipaRepo;
            _participantiRepo = participantiRepo;
            _cursaRepo = cursaRepo;
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
            List<Utilizator> listOfUsers=_utilizatorRepo.FindAll().ToList();
            foreach (Utilizator utilizator in listOfUsers)
            {
                if (utilizator.NumeUtilizator.Equals(user.NumeUtilizator) && utilizator.Passwd.Equals(user.Passwd))
                {
                    listaObeserveri.Add(raceController);
                    return;
                }
            }

            throw new Exception("Utilizator invalid!");
        }

        public void logOut(IRaceObserver raceObserver)
        {
            listaObeserveri.Remove(raceObserver);
        }

        public object getAllForStage()
        {
            List<Cursa> curse = (List<Cursa>) _cursaRepo.FindAll();
            List<ParticipantiCursa> participantiCursas = (List<ParticipantiCursa>) _participantiRepo.FindAll();
            List<Echipa> echipe = (List<Echipa>) _echipaRepo.FindAll();
            List<int> noParticipanti = new List<int>();
            foreach (var cursa in curse)
            {
                int number = 0;
                foreach (var participant in participantiCursas)
                {
                    if (participant.IdCursa.Equals(cursa.Id))
                        number++;
                }
                noParticipanti.Add(number);
            }

            AllForStageDTO rez = new AllForStageDTO(curse, noParticipanti, echipe);
            return rez;
        }

        public List<Sofer> getParticipanti(int idCursa)
        {
            List<ParticipantiCursa> participantiCurse = _participantiRepo.FindAll() as List<ParticipantiCursa>;
            List<Sofer> listaSoferi = _soferRepo.FindAll() as List<Sofer>;
            participantiCurse = participantiCurse.FindAll(participant => participant.IdCursa.Equals(idCursa));
            var rez = new List<Sofer>();
            foreach (Sofer sofer in listaSoferi)
            {
                foreach (var participant in participantiCurse)
                {
                    if(participant.IdSofer.Equals(sofer.Id))
                        rez.Add(sofer);
                }
            }

            return rez;
        }

        public List<Sofer> getTeam(string s)
        {
            List<Echipa> echipe = _echipaRepo.FindAll() as List<Echipa>;
            List<Sofer> listaSoferi = _soferRepo.FindAll() as List<Sofer>;
            Echipa echipa = echipe.Find(echipa1 => echipa1.NumeEchipa.Equals(s));
            listaSoferi = listaSoferi.FindAll(sofer => sofer.IdEchipa.Equals(echipa.Id));
            return listaSoferi;
        }

        public void inscrie(string nume, string nrMasina, string echipa, int idCursa)
        {
            List<Echipa> echipe = _echipaRepo.FindAll() as List<Echipa>;
            Echipa echipaCautata = echipe.Find(echipa1 => echipa1.NumeEchipa.Equals(echipa));
            List<Cursa> curse = _cursaRepo.FindAll() as List<Cursa>;
            Cursa cursaCautata = curse.Find(cursa => cursa.Id.Equals(idCursa));
            
            Masina masina = new Masina(nrMasina, cursaCautata.CapacitateMotor);
            _masinaRepo.Save(masina);
            List<Masina> masini = _masinaRepo.FindAll() as List<Masina>;
            masina = masini.Find(masina1 => masina1.NrMasina.Equals(masina.NrMasina));
            Sofer sofer = new Sofer(nume, echipaCautata.Id, masina.Id);
            _soferRepo.Save(sofer);
            List<Sofer> soferi = _soferRepo.FindAll() as List<Sofer>;
            sofer = soferi.Find(sofer1 =>
                sofer1.NumeSofer.Equals(sofer.NumeSofer) && sofer1.IdMasina.Equals(sofer.IdMasina));
            ParticipantiCursa participantNou = new ParticipantiCursa(sofer.Id, cursaCautata.Id);
            _participantiRepo.Save(participantNou);
            
            foreach (var raceObserver in listaObeserveri)
            {
                Task.Run(() => raceObserver.update());
            }
        }
    }
}