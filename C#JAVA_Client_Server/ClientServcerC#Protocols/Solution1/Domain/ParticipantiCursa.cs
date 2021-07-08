using System;

namespace Domain
{
    [Serializable]
    public class ParticipantiCursa : IObject<int>
    {
        private int idSofer;
        private int idCursa;

        public int IdSofer
        {
            get => idSofer;
            set => idSofer = value;
        }

        public int IdCursa
        {
            get => idCursa;
            set => idCursa = value;
        }

        

        public override string ToString()
        {
            return base.ToString()+" "+idSofer.ToString()+" "+idCursa.ToString();
        }

        public ParticipantiCursa(int idSofer, int idCursa)
        {
            this.idSofer = idSofer;
            this.idCursa = idCursa;
        }

        public ParticipantiCursa()
        {
            
        }

        public int Id { get; set; }
    }
}