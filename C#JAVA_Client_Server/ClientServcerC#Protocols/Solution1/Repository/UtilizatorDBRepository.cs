using System;
using System.Collections.Generic;
using ConsoleApp1.domain;
using Domain.validators.interfaceAndException;
using MySql.Data.MySqlClient;

namespace Repository
{
    public class UtilizatorDBRepository : IObjectRepo<Utilizator,int>
    {
        private IValidator<Utilizator> _validator;
        private Utils.Utils _utils;

        public UtilizatorDBRepository(IValidator<Utilizator> validator)
        {
            _validator = validator;
            _utils = new Utils.Utils();
        }

        public Utilizator FindOne(int id)
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "select * from utilizator where idUtilizator=@id";
                commander = new MySqlCommand(sql, connection);
                commander.Parameters.AddWithValue("@id", id);
                MySqlDataReader reader = commander.ExecuteReader();
                Utilizator utilizator = new Utilizator();
                reader.Read();
                utilizator.NumeUtilizator = reader.GetString(1);
                utilizator.Id = reader.GetInt32(0);
                return utilizator;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public IEnumerable<Utilizator> FindAll()
        {
            var connection = _utils.GetConnection();
            var commander =_utils.GetCommander(connection);
            try
            {
                List<Utilizator> utilizators = new List<Utilizator>();
                connection.Open();
                var sql = "select * from utilizator";
                commander = new MySqlCommand(sql, connection);

                MySqlDataReader reader = commander.ExecuteReader();
                while (reader.Read())
                {
                    Utilizator utilizator = new Utilizator(reader.GetString(1),reader.GetString(2));
                    utilizator.Id = reader.GetInt32(0);
                    utilizators.Add(utilizator);
                }

                return utilizators;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public void Save(Utilizator entity)
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
            var commander =_utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "Insert into utilizator(user) values(@user)";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@user", entity.NumeUtilizator);
                commander.Prepare();

                commander.ExecuteNonQuery();
                
                Console.WriteLine("Row Inserted");
                connection.Close();

            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }        }

        public void Update(int id,Utilizator utilizator)
        {
            try
            {
                _validator.validate(utilizator);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return;
            }
            throw new System.NotImplementedException();
        }

        public void Delete(int id)
        {
            throw new System.NotImplementedException();
        }
        
    }
}