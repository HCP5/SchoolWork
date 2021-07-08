using System;

namespace Domain
{
    [Serializable]
    public class Sofer : IObject<int>
    {
        String numeSofer;
        private int idEchipa;
        private int idMasina;

        public string NumeSofer
        {
            get => numeSofer;
            set => numeSofer = value;
        }

        public int IdEchipa
        {
            get => idEchipa;
            set => idEchipa = value;
        }

        public int IdMasina
        {
            get => idMasina;
            set => idMasina = value;
        }

        public Sofer(string numeSofer, int idEchipa, int idMasina)
        {
            this.numeSofer = numeSofer;
            this.idEchipa = idEchipa;
            this.idMasina = idMasina;
        }

        public override string ToString()
        {
            return base.ToString()+" "+numeSofer+" "+idEchipa.ToString()+" "+idMasina.ToString();
        }

        public Sofer()
        {
            
        }

        public int Id { get; set; }
    }
}