using System.Collections.Generic;
using Domain;

namespace Service
{
    public interface ISoferService
    {
        List<Sofer> GetAllSoferi();
        void AddSofer(Sofer sofer);
    }
}