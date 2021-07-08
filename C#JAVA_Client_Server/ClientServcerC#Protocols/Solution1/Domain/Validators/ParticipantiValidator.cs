using System;
using Domain.validators.interfaceAndException;

namespace Domain.validators
{
    public class ParticipantiValidator : IValidator<ParticipantiCursa>
    {
        public void validate(ParticipantiCursa entity)
        {
            if (entity.IdCursa.Equals(null) || entity.IdSofer.Equals(null))
            {
                throw new Exception("Participant INVALID!");
            }
        }
    }
}