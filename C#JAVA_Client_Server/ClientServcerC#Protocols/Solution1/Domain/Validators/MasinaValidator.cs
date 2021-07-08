using System;
using Domain.validators.interfaceAndException;

namespace Domain.validators
{
    public class MasinaValidator : IValidator<Masina>
    {
        public void validate(Masina entity)
        {
            if (entity.NrMasina.Length == 0 || entity.CapacitateMotor.Equals(null))
            {
                throw new Exception("Masina INVALIDA!");
            }
        }
    }
}