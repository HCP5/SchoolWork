using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Forms;
using Domain;

namespace Client
{
    public partial class RegisterRacer : Form
    {
        private readonly RaceController controller;
        private readonly UserWindow parentWindow;
        private int cursaSelectata;
        private readonly List<Echipa> lista;

        public RegisterRacer(RaceController controller, UserWindow userWindow,List<Echipa> lista)
        {  
            InitializeComponent();
            this.controller = controller;
            parentWindow = userWindow;
            this.lista = lista;

            var binding = new BindingList<string>();
            comboBox1.DataSource = binding;
            foreach (var echipa in lista)
            {
                binding.Add(echipa.NumeEchipa);
            }
        }

        public void setCursa(int idCursa)
        {
            cursaSelectata = idCursa;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var nume = this.nume.Text;
            var nrMasina = this.nrMasina.Text;
            var echipa = this.comboBox1.SelectedItem.ToString();
            var idCursa = cursaSelectata;
            controller.inscrieRacer(nume, nrMasina, echipa, idCursa);
            this.Hide();
            parentWindow.Show();
        }
    }
}