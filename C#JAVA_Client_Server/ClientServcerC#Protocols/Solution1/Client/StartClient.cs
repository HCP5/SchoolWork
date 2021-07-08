using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mime;
using System.Threading.Tasks;
using System.Windows.Forms;
using Networking.clientSide;
using Server;

namespace Client
{
    static class StartClient
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            IMasterService server = new RaceClientProxy("127.0.0.1", 55555);
            RaceController controller = new RaceController(server);
            LoginWindow window = new LoginWindow(controller);
            Application.Run(window);
        }
    }
}