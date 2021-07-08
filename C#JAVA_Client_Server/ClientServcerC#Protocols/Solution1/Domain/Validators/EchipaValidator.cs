using System;
using Domain.validators.interfaceAndException;

namespace Domain.validators
{
    public class EchipaValidator : IValidator<Echipa>  
    {
        public void validate(Echipa entity)
        {
            if (entity.NumeEchipa.Length==0)
            {
                throw new Exception("Echipa INVALIDA!");
            }
        }
    }
}