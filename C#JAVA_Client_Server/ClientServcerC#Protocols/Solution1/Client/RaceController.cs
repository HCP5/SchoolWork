using System.Collections.Generic;
using System.Threading.Tasks;
using ConsoleApp1.domain;
using Domain;
using Networking.dtos;
using Server;
using Service;

namespace Client
{
    public class RaceController : IRaceObserver
    {
        private readonly IMasterService server;
        private Utilizator curentUtilizotr;
        private UserWindow windowForUpdate;

        public RaceController(IMasterService server)
        {
            this.server = server;
            this.curentUtilizotr = null;
        }

        public void login(string user, string passwd)
        {
            Utilizator utilizator = new Utilizator(user, passwd);
            server.login(utilizator, this);
        }

        public void logOut()
        {
            server.logOut(this);
        }

        public AllForStageDTO prepareScene()
        {
            AllForStageDTO allThatINeed = (AllForStageDTO)server.getAllForStage();
            return allThatINeed;
        }

        public List<Sofer> getThisRace(int idCursa)
        {
            List<Sofer> raceDetail = server.getParticipanti(idCursa);
            return raceDetail;
        }

        public List<Sofer> getThisTeam(string s)
        {
            List<Sofer> echipaDetail = server.getTeam(s);
            return echipaDetail;
        }

        public void inscrieRacer(string nume, string nrMasina, string echipa, int idCursa)
        {
            server.inscrie(nume, nrMasina, echipa, idCursa);
        }

        public void update()
        {
            Task.Run(()=>windowForUpdate.updateList());
        }

        public void setWindowForUpdate(UserWindow userWindow)
        {
            this.windowForUpdate = userWindow;
        }
    }
}