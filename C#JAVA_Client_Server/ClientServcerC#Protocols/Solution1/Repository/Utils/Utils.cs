
using System.Configuration;
using MySql.Data.MySqlClient;

namespace Repository.Utils
{
    public class Utils
    {
        private string _connectionString;
        

        public Utils()
        {
            _connectionString = ConfigurationManager.ConnectionStrings["mySql"].ConnectionString;
        }

        public MySqlConnection GetConnection()
        {
            return new MySqlConnection(_connectionString);
        }

        public MySqlCommand GetCommander(MySqlConnection connection)
        {
            var commander = new MySqlCommand();
            commander.Connection = connection;
            return commander;
        }
    }
}