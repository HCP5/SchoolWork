using System.Collections.Generic;
using ConsoleApp1.domain;
using Domain;
using Service;

namespace Server
{
    public interface IMasterService : ICursaService,IEchipaService,IMasinaService,IParticipantiService,ISoferService,IUtilizatorService
    {
        void login(Utilizator user, IRaceObserver raceController);
        void logOut(IRaceObserver raceObserver);
        object getAllForStage();
        List<Sofer> getParticipanti(int idCursa);
        List<Sofer> getTeam(string s);
        void inscrie(string nume, string nrMasina, string echipa, int idCursa);
    }
}