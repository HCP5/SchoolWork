using System;
using System.Collections.Generic;

namespace Domain
{
    [Serializable]
    public class Cursa : IObject<int>
    {
        private String numeCupa;
        private int capacitateMotor;

        public override string ToString()
        {
            return base.ToString()+" "+numeCupa.ToString()+" "+capacitateMotor.ToString();
        }

        public string NumeCupa
        {
            get => numeCupa;
            set => numeCupa = value;
        }

        public int CapacitateMotor
        {
            get => capacitateMotor;
            set => capacitateMotor = value;
        }

        public Cursa(string numeCupa, int capacitateMotor)
        {
            this.numeCupa = numeCupa;
            this.capacitateMotor = capacitateMotor;
        }
        
        public Cursa()
        { 
            
        }

        public int Id { get; set; }
    }
}