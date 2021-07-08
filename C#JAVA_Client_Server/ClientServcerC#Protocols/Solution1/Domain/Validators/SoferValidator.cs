using System;
using Domain.validators.interfaceAndException;

namespace Domain.validators
{
    public class SoferValidator : IValidator<Sofer>
    {
        public void validate(Sofer entity)
        {
            if (entity.NumeSofer.Length == 0 || entity.IdEchipa.Equals(null) || entity.IdMasina.Equals(null))
            {
                throw new Exception("Sofer INVALID!");
            }
        }
    }
}