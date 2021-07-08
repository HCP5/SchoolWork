using System;

namespace Domain
{
    [Serializable]
    public class Masina : IObject<int>
    {
        String nrMasina;
        int capacitateMotor;

        public string NrMasina
        {
            get => nrMasina;
            set => nrMasina = value;
        }


        public override string ToString()
        {
            return base.ToString()+" "+nrMasina+" "+capacitateMotor.ToString();
        }

        public int CapacitateMotor
        {
            get => capacitateMotor;
            set => capacitateMotor = value;
        }

        public Masina(string nrMasina, int capacitateMotor)
        {
            this.nrMasina = nrMasina;
            this.capacitateMotor = capacitateMotor;
        }

        public Masina()
        {
            
        }

        public int Id { get; set; }
    }
}