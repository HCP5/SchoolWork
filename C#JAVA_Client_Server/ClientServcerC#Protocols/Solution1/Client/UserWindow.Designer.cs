using System.ComponentModel;

namespace Client
{
    partial class UserWindow
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }

            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.Races = new System.Windows.Forms.DataGridView();
            this.Racers = new System.Windows.Forms.DataGridView();
            this.LogOutButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.ShowStaffButton = new System.Windows.Forms.Button();
            this.echipaBox = new System.Windows.Forms.ComboBox();
            this.ButonDeInscriere = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize) (this.Races)).BeginInit();
            ((System.ComponentModel.ISupportInitialize) (this.Racers)).BeginInit();
            this.SuspendLayout();
            // 
            // Races
            // 
            this.Races.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Races.Location = new System.Drawing.Point(12, 12);
            this.Races.Name = "Races";
            this.Races.RowTemplate.Height = 24;
            this.Races.Size = new System.Drawing.Size(370, 293);
            this.Races.TabIndex = 0;
            this.Races.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.getCurse);
            // 
            // Racers
            // 
            this.Racers.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Racers.Location = new System.Drawing.Point(12, 311);
            this.Racers.Name = "Racers";
            this.Racers.RowTemplate.Height = 24;
            this.Racers.Size = new System.Drawing.Size(370, 293);
            this.Racers.TabIndex = 1;
            // 
            // LogOutButton
            // 
            this.LogOutButton.Location = new System.Drawing.Point(388, 554);
            this.LogOutButton.Name = "LogOutButton";
            this.LogOutButton.Size = new System.Drawing.Size(138, 49);
            this.LogOutButton.TabIndex = 2;
            this.LogOutButton.Text = "LogOut";
            this.LogOutButton.UseVisualStyleBackColor = true;
            this.LogOutButton.Click += new System.EventHandler(this.LogOutButton_Click);
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(399, 311);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(100, 23);
            this.label1.TabIndex = 4;
            this.label1.Text = "Echipa";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // ShowStaffButton
            // 
            this.ShowStaffButton.Location = new System.Drawing.Point(399, 367);
            this.ShowStaffButton.Name = "ShowStaffButton";
            this.ShowStaffButton.Size = new System.Drawing.Size(100, 23);
            this.ShowStaffButton.TabIndex = 5;
            this.ShowStaffButton.Text = "Show staff";
            this.ShowStaffButton.UseVisualStyleBackColor = true;
            this.ShowStaffButton.Click += new System.EventHandler(this.ShowStaffButton_Click);
            // 
            // echipaBox
            // 
            this.echipaBox.FormattingEnabled = true;
            this.echipaBox.Location = new System.Drawing.Point(388, 337);
            this.echipaBox.Name = "echipaBox";
            this.echipaBox.Size = new System.Drawing.Size(121, 24);
            this.echipaBox.TabIndex = 6;
            // 
            // ButonDeInscriere
            // 
            this.ButonDeInscriere.Location = new System.Drawing.Point(388, 425);
            this.ButonDeInscriere.Name = "ButonDeInscriere";
            this.ButonDeInscriere.Size = new System.Drawing.Size(121, 52);
            this.ButonDeInscriere.TabIndex = 7;
            this.ButonDeInscriere.Text = "Inscrie";
            this.ButonDeInscriere.UseVisualStyleBackColor = true;
            this.ButonDeInscriere.Click += new System.EventHandler(this.ButonDeInscriere_Click);
            // 
            // UserWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(542, 615);
            this.Controls.Add(this.ButonDeInscriere);
            this.Controls.Add(this.echipaBox);
            this.Controls.Add(this.ShowStaffButton);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.LogOutButton);
            this.Controls.Add(this.Racers);
            this.Controls.Add(this.Races);
            this.Name = "UserWindow";
            this.Text = "UserWindow";
            ((System.ComponentModel.ISupportInitialize) (this.Races)).EndInit();
            ((System.ComponentModel.ISupportInitialize) (this.Racers)).EndInit();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Button ButonDeInscriere;

        private System.Windows.Forms.ComboBox echipaBox;

        private System.Windows.Forms.Button ShowStaffButton;

        private System.Windows.Forms.Button button1;

        private System.Windows.Forms.Label label1;

        private System.Windows.Forms.DataGridView Racers;
        private System.Windows.Forms.DataGridView Races;
        private System.Windows.Forms.Button LogOutButton;

        private System.Windows.Forms.DataGridView dataGridView2;

        private System.Windows.Forms.DataGridView dataGridView1;

        #endregion
    }
}