using System;
using System.Collections.Generic;

using Domain;
using Domain.validators.interfaceAndException;
using MySql.Data.MySqlClient;

namespace Repository
{
    public class EchipaDBRepository: IObjectRepo<Echipa,int>
    {
        private IValidator<Echipa> _validator;
        private MySqlDataAdapter dataAdapter=new MySqlDataAdapter();
        private Utils.Utils _utils;

        public EchipaDBRepository(IValidator<Echipa> validator)
        {
            _validator = validator;
            _utils = new Utils.Utils();
        }

        public Echipa FindOne(int id)
        {
            var connection = _utils.GetConnection();
            var commander =_utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "select * from echipa where idEchipa=@idEchipa";
                commander = new MySqlCommand(sql, connection);
                commander.Parameters.AddWithValue("@idEchipa", id);
                MySqlDataReader reader = commander.ExecuteReader();
                Echipa echipa = new Echipa();
                reader.Read();
                echipa.NumeEchipa = reader.GetString(1);
                echipa.Id = reader.GetInt32(0);
                return echipa;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public IEnumerable<Echipa> FindAll()
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                List<Echipa> echipe = new List<Echipa>();
                connection.Open();
                var sql = "select * from echipa";
                commander = new MySqlCommand(sql, connection);

                MySqlDataReader reader = commander.ExecuteReader();
                while (reader.Read())
                {
                    Echipa echipa = new Echipa(reader.GetString(1));
                    echipa.Id = reader.GetInt32(0);
                    echipe.Add(echipa);
                }

                return echipe;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public void Save(Echipa entity)
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
                var sql = "Insert into echipa(numeEchipa) values(@numeEchipa)";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@numeEchipa", entity.NumeEchipa);
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

        public void Update(int id,Echipa echipa)
        {
            try
            {
                _validator.validate(echipa);
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

        public MySqlDataAdapter getDataAdapter()
        {
            
            dataAdapter.SelectCommand = new MySqlCommand("select * from echipa", _utils.GetConnection());
            return dataAdapter;
        }
    }
}