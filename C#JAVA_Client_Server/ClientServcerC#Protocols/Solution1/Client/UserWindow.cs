using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Windows.Forms;
using Domain;
using Networking.dtos;

namespace Client
{
    public partial class UserWindow : Form
    {
        private RaceController controller;
        private LoginWindow myParent;
        private List<Echipa> echipe;

        public UserWindow()
        {
            InitializeComponent();
            

        }

        private void LogOutButton_Click(object sender, EventArgs e)
        {
            controller.logOut();
            this.Hide();
            myParent.Show();
        }

        public void setController(RaceController controller, LoginWindow loginWindow)
        {
            this.controller = controller;
            myParent = loginWindow;
            controller.setWindowForUpdate(this);
            AllForStageDTO data=controller.prepareScene();
            var bindings = new BindingList<RowForStage>();
            Races.DataSource = bindings;
            List<Cursa> curse =data.ListOfCurse;
            List<int> participanti=data.ListOfParticipanti;
            for (int i = 0; i < curse.Count; ++i)
            {
                bindings.Add(new RowForStage(curse[i].Id,curse[i].NumeCupa,curse[i].CapacitateMotor.ToString(),participanti[i]));
            }

            var echipaBindings = new BindingList<string>();
            List<Echipa> echipe = data.ListOfEchipe;
            this.echipe = echipe;
            echipaBox.DataSource = echipaBindings;
            foreach (var echipa in echipe)
            {
                echipaBindings.Add(echipa.NumeEchipa);
            }
            Races.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
        }

        private void getCurse(object sender, EventArgs e)
        {
            int idCursa = (int) Races.SelectedRows[0].Cells[0].Value;
            List<Sofer>lista=controller.getThisRace(idCursa);
            var Binding = new BindingList<Sofer>();
            Racers.DataSource = Binding;
            foreach (var sofer in lista)
            {
                Binding.Add(sofer);
            }
        }

        private void ShowStaffButton_Click(object sender, EventArgs e)
        {
            string x = (string) echipaBox.SelectedItem;
            List<Sofer> soferi = controller.getThisTeam(x);

            var binding = new BindingList<Sofer>();
            Racers.DataSource = binding;
            foreach (var sofer in soferi)
            {
                binding.Add(sofer);
            }

        }

        private void ButonDeInscriere_Click(object sender, EventArgs e)
        {
            RegisterRacer registerRacer = new RegisterRacer(controller,this,echipe);
            int idCursa = (int) Races.SelectedRows[0].Cells[0].Value;
            registerRacer.setCursa(idCursa);
            this.Hide();
            registerRacer.Show();
        }

        public void updateList()
        {
            
            AllForStageDTO data=controller.prepareScene();
            var bindings = new BindingList<RowForStage>();
            Races.DataSource = bindings;
            List<Cursa> curse =data.ListOfCurse;
            List<int> participanti=data.ListOfParticipanti;
            for (int i = 0; i < curse.Count; ++i)
            {
                bindings.Add(new RowForStage(curse[i].Id,curse[i].NumeCupa,curse[i].CapacitateMotor.ToString(),participanti[i]));
            }

            var echipaBindings = new BindingList<string>();
            List<Echipa> echipe = data.ListOfEchipe;
            this.echipe = echipe;
            echipaBox.DataSource = echipaBindings;
            foreach (var echipa in echipe)
            {
                echipaBindings.Add(echipa.NumeEchipa);
            }
        }
    }

    public class RowForStage
    {
        private int idCursa;
        private string numeCursa;
        private string capacitate;
        private int noParticipanti;

        public RowForStage(int idCursa,string numeCursa, string capacitate, int noParticipanti)
        {
            this.idCursa = idCursa;
            this.numeCursa = numeCursa;
            this.capacitate = capacitate;
            this.noParticipanti = noParticipanti;
        }

        public int IdCursa => idCursa;

        public string NumeCursa => numeCursa;

        public string Capacitate => capacitate;

        public int NoParticipanti => noParticipanti;
    }
}