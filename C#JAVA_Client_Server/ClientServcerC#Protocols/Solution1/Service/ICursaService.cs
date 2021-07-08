using System.Collections.Generic;
using Domain;

namespace Service
{
    public interface ICursaService
    {
        List<Cursa> GetAllRaces();
    }
}