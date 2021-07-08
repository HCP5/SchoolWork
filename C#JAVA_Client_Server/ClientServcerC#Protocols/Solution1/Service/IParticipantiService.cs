using System.Collections.Generic;
using Domain;

namespace Service
{
    public interface IParticipantiService
    {
        List<ParticipantiCursa> GetAllParticipanti();
        void AddParticipant();
    }
}