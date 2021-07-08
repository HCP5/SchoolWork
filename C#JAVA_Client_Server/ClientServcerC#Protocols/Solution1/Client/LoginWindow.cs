using System;
using System.Windows.Forms;

namespace Client
{
    public partial class LoginWindow : Form
    {
        private RaceController _controller;
        public LoginWindow(RaceController controller)
        {
            InitializeComponent();
            this._controller = controller;
        }

        private void LogInButton_Click(object sender, EventArgs e)
        {
            string user = userTextBox.Text;
            string passwd = passwordTextBox.Text;
            try
            {
                _controller.login(user, passwd);
                MessageBox.Show("Login succeded");
                UserWindow userWindow = new UserWindow();
                userWindow.setController(_controller,this);
                userWindow.Show();
                this.Hide();
            }
            catch (Exception exception)
            {
                MessageBox.Show(exception.Message);
            }
        }
    }
}