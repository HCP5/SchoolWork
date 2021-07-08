using System;
using System.Collections.Generic;


namespace Domain
{
    [Serializable]
    public class Echipa : IObject<int>
    {
        String numeEchipa;

        public override string ToString()
        {
            return base.ToString()+" "+numeEchipa.ToString();
        }

        public Echipa(string numeEchipa)
        {
            this.numeEchipa = numeEchipa;
        }

        public Echipa()
        {
            
        }

        public string NumeEchipa
        {
            get => numeEchipa;
            set => numeEchipa = value;
        }
        
        public int Id { get; set; }
    }
}