using System;
using Domain;

namespace ConsoleApp1.domain
{
    [Serializable]
    public class Utilizator : IObject<int>
    {
        private String numeUtilizator;
        private string passwd;

        public Utilizator(string numeUtilizator, string passwd)
        {
            this.numeUtilizator = numeUtilizator;
            this.passwd = passwd;
        }

        public Utilizator(string numeUtilizator)
        {
            this.numeUtilizator = numeUtilizator;
        }

        public Utilizator()
        {
            
        }

        public override string ToString()
        {
            return base.ToString()+" "+numeUtilizator;
        }

        public string NumeUtilizator
        {
            get => numeUtilizator;
            set => numeUtilizator = value;
        }

        public int Id { get; set; }
        public string Passwd
        {
            get => passwd;
        }
    }
}