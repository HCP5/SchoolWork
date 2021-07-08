using System;
using ConsoleApp1.domain;

namespace Networking.protocols
{
    public interface Request
    {
    }

    [Serializable]
    public class LoginRequest : Request
    {
        private Utilizator user;

        public LoginRequest(Utilizator user)
        {
            this.user = user;
        }

        public Utilizator User => user;
    }

    [Serializable]
    public class LogOutRequest : Request
    {
        
    }

    [Serializable]
    public class GetAllRequest : Request
    {
        
    }

    [Serializable]
    public class GetRaceDetailsRequest : Request
    {
        private int idCursa;

        public GetRaceDetailsRequest(int idCursa)
        {
            this.idCursa = idCursa;
        }

        public int IdCursa => idCursa;
    }

    [Serializable]
    public class GetTeamDetailsRequest : Request
    {
        private string numeEchipa;

        public GetTeamDetailsRequest(string numeEchipa)
        {
            this.numeEchipa = numeEchipa;
        }

        public string NumeEchipa => numeEchipa;
    }

    [Serializable]
    public class InscrieRequest : Request
    {
        private readonly string nume;
        private readonly string nrMasina;
        private readonly string echipa;
        private readonly int idCursa;

        public InscrieRequest(string nume, string nrMasina, string echipa, int idCursa)
        {
            this.nume = nume;
            this.nrMasina = nrMasina;
            this.echipa = echipa;
            this.idCursa = idCursa;
        }

        public string Nume => nume;

        public string NrMasina => nrMasina;

        public string Echipa => echipa;

        public int IdCursa => idCursa;
    }
}