using System;
using System.Collections.Generic;
using Domain;
using Domain.validators.interfaceAndException;
using MySql.Data.MySqlClient;

namespace Repository
{
    public class SoferDBRepository : IObjectRepo<Sofer,int>
    {
        private IValidator<Sofer> _validator;
        private Utils.Utils _utils;

        public SoferDBRepository(IValidator<Sofer> validator)
        {
            _validator = validator;
            _utils = new Utils.Utils();
        }

        public Sofer FindOne(int id)
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "select * from sofer where idSofer=@id";
                commander = new MySqlCommand(sql, connection);
                commander.Parameters.AddWithValue("@id", id);
                MySqlDataReader reader = commander.ExecuteReader();
                Sofer sofer = new Sofer();
                reader.Read();
                sofer.NumeSofer = reader.GetString(1);
                sofer.IdEchipa = reader.GetInt32(2);
                sofer.IdMasina = reader.GetInt32(3);
                
                sofer.Id = reader.GetInt32(0);
                return sofer;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public IEnumerable<Sofer> FindAll()
        {
            
            var connection =_utils.GetConnection();
            var commander =_utils.GetCommander(connection);
            try
            {
                List<Sofer> soferi = new List<Sofer>();
                connection.Open();
                var sql = "select * from sofer";
                commander = new MySqlCommand(sql, connection);

                MySqlDataReader reader = commander.ExecuteReader();
                while (reader.Read())
                {
                    Sofer sofer = new Sofer(reader.GetString(1),reader.GetInt32(2),reader.GetInt32(3));
                    sofer.Id = reader.GetInt32(0);
                    soferi.Add(sofer);
                }

                return soferi;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }        }

        public void Save(Sofer entity)
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
                var sql = "Insert into sofer(numeSofer,idEchipa,idMasina) values(@numeSofer,@idEchipa,@idMasina)";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@numeSofer", entity.NumeSofer);
                commander.Parameters.AddWithValue("@idEchipa", entity.IdEchipa);
                commander.Parameters.AddWithValue("@idMasina", entity.IdMasina);
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

        public void Update(int id,Sofer sofer)
        {
            try
            {
                _validator.validate(sofer);
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