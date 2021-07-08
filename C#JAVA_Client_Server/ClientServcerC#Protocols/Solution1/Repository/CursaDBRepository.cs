using System;
using System.Collections.Generic;
using Domain;
using Domain.validators.interfaceAndException;
using MySql.Data.MySqlClient;

namespace Repository
{
    public class CursaDBRepository : IObjectRepo<Cursa,int>
    {
        private IValidator<Cursa> _validator;

        private MySqlDataAdapter dataAdapter=new MySqlDataAdapter();
        private Utils.Utils _utils;

        public CursaDBRepository(IValidator<Cursa> validator)
        {
            _validator = validator;
            _utils = new Utils.Utils();
        }

        public Cursa FindOne(int id)
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "select * from cursa where idCursa=@idCursa";
                commander = new MySqlCommand(sql, connection);
                commander.Parameters.AddWithValue("@idCursa", id);
                MySqlDataReader reader = commander.ExecuteReader();
                Cursa cursa = new Cursa();
                reader.Read();
                cursa.NumeCupa = reader.GetString(2);
                cursa.CapacitateMotor = reader.GetInt32(1);
                cursa.Id = reader.GetInt32(0);
                return cursa;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public MySqlDataAdapter getDataAdapter()
        {
            dataAdapter.SelectCommand = new MySqlCommand("select * from cursa", _utils.GetConnection());
            return dataAdapter;
        }
        public IEnumerable<Cursa> FindAll()
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                List<Cursa> curse = new List<Cursa>();
                
                connection.Open();
                var sql = "select * from cursa";
                commander = new MySqlCommand(sql, connection);
                MySqlDataReader reader = commander.ExecuteReader();
                while (reader.Read())
                {
                    Cursa cursa = new Cursa(reader.GetString(2), reader.GetInt32(1));
                    cursa.Id = reader.GetInt32(0);
                    curse.Add(cursa);
                }

                return curse;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public void Save(Cursa entity)
        {
            try
            {
                _validator.validate(entity);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return;
            }
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "Insert into cursa(capacitateMotor,numeCupa) values(@capacitate ,@numeCupa)";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@capacitate", entity.CapacitateMotor);
                commander.Parameters.AddWithValue("@numeCupa", entity.CapacitateMotor);
                commander.Prepare();

                commander.ExecuteNonQuery();
                
                Console.WriteLine("Row Inserted");
                connection.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public void Update(int id,Cursa cursa)
        {
            try
            {
                _validator.validate(cursa);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return;
            }
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "update cursa set capacitateMotr=@capacitateMotor where idCursa=@idCursa";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@capacitateMotor", 2000);
                commander.Parameters.AddWithValue("@idCursa", id);
                commander.Prepare();

                commander.ExecuteNonQuery();
                
                Console.WriteLine("Row updated");
                connection.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }        }

        public void Delete(int id)
        {
            var connection =_utils.GetConnection();
            var commander =_utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "delete from cursa where idCursa=@idCursa";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@idCursa", id);
                commander.Prepare();

                commander.ExecuteNonQuery();
                
                Console.WriteLine("Row deleted");
                connection.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }        
        }
    }
}