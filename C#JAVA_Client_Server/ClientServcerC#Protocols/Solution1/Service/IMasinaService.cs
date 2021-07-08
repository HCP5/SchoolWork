using System.Collections.Generic;
using Domain;

namespace Service
{
    public interface IMasinaService
    {
        List<Masina> GetAllCars();
        void AddCar(Masina masina);
    }
}