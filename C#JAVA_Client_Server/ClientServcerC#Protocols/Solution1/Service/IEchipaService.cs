using System.Collections.Generic;
using Domain;

namespace Service
{
    public interface IEchipaService
    {
        List<Echipa> GetAllTeams();
    }
}