using System;
using System.Collections.Generic;
using Domain;
using Domain.validators.interfaceAndException;
using MySql.Data.MySqlClient;

namespace Repository
{
    public class MasinaDBRepository: IObjectRepo<Masina,int>
    {
        private IValidator<Masina> _validator;
        private Utils.Utils _utils;

        public MasinaDBRepository(IValidator<Masina> validator)
        {
            _validator = validator;
            _utils = new Utils.Utils();
        }

        public Masina FindOne(int id)
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "select * from masina where idMasina=@idMasina";
                commander = new MySqlCommand(sql, connection);
                commander.Parameters.AddWithValue("@idMasina", id);
                MySqlDataReader reader = commander.ExecuteReader();
                Masina masina = new Masina();
                reader.Read();
                masina.NrMasina = reader.GetString(1);
                masina.Id = reader.GetInt32(0);
                masina.CapacitateMotor = reader.GetInt32(2);
                return masina;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public IEnumerable<Masina> FindAll()
        {
            var connection = _utils.GetConnection();
            var commander = _utils.GetCommander(connection);
            try
            {
                List<Masina> masini = new List<Masina>();
                connection.Open();
                var sql = "select * from masina";
                commander = new MySqlCommand(sql, connection);

                MySqlDataReader reader = commander.ExecuteReader();
                while (reader.Read())
                {
                    Masina masina = new Masina(reader.GetString(1), reader.GetInt32(2));
                    masina.Id = reader.GetInt32(0);
                    masini.Add(masina);
                }

                return masini;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public void Save(Masina entity)
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
                var sql = "Insert into masina(nrMasina,capacitateMotor) values(@nrMasina,@capacitateMotor)";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@nrMasina", entity.NrMasina);
                commander.Parameters.AddWithValue("@capacitateMotor", entity.CapacitateMotor);
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

        public void Update(int id,Masina masina)
        {
            try
            {
                _validator.validate(masina);
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
            throw new NotImplementedException();
        }
    }
}