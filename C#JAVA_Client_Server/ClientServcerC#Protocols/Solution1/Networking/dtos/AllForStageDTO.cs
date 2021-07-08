using System;
using System.Collections.Generic;
using Domain;

namespace Networking.dtos
{
    [Serializable]
    public class AllForStageDTO
    {
        private List<Cursa> listOfCurse = new List<Cursa>();
        private List<int> listOfParticipanti = new List<int>();
        private List<Echipa> listOfEchipe = new List<Echipa>();

        public AllForStageDTO(List<Cursa> curse, List<int> participanti, List<Echipa> echipe)
        {
            listOfCurse = curse;
            listOfParticipanti = participanti;
            listOfEchipe = echipe;
        }

        public List<Cursa> ListOfCurse
        {
            get => listOfCurse;
            set => listOfCurse = value;
        }

        public List<int> ListOfParticipanti
        {
            get => listOfParticipanti;
            set => listOfParticipanti = value;
        }

        public List<Echipa> ListOfEchipe
        {
            get => listOfEchipe;
            set => listOfEchipe = value;
        }
    }
}