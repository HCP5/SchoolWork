using System;
using Domain.validators.interfaceAndException;

namespace ConsoleApp1.domain.validators
{
    public class UtilizatorValidator : IValidator<Utilizator>
    {
        public void validate(Utilizator entity)
        {
            if (entity.NumeUtilizator.Length == 0 )
            {
                throw new Exception ("Utilizator INVALID!");
            }
        }
    }
}