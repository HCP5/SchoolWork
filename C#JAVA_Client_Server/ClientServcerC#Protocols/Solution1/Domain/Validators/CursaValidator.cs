using System;
using Domain.validators.interfaceAndException;


namespace Domain.validators
{
    public class CursaValidator : IValidator<Cursa>
    {
        public void validate(Cursa entity)
        {
            if (entity.NumeCupa.Length == 0 || entity.CapacitateMotor.Equals(null))
            {
                throw new Exception("Cursa INVALIDA!");
            }
        }
    }
}