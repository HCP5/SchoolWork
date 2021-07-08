using System;
using System.Collections.Generic;
using Domain;
using Domain.validators.interfaceAndException;
using MySql.Data.MySqlClient;

namespace Repository
{
    public class ParticipantiDBRepository : IObjectRepo<ParticipantiCursa,int>
    {
        private IValidator<ParticipantiCursa> _validator;
        private Utils.Utils _utils; 

        public ParticipantiDBRepository(IValidator<ParticipantiCursa> validator)
        {
            _validator = validator;
            _utils = new Utils.Utils();
        }

        public ParticipantiCursa FindOne(int id)
        {
            var connection = _utils.GetConnection();
            var commander =_utils.GetCommander(connection);
            try
            {
                connection.Open();
                var sql = "select * from participanticursa where idParticipant=@id";
                commander = new MySqlCommand(sql, connection);
                commander.Parameters.AddWithValue("@id", id);
                MySqlDataReader reader = commander.ExecuteReader();
                ParticipantiCursa participantiCursa =new ParticipantiCursa();
                reader.Read();
                participantiCursa.Id = reader.GetInt32(0);
                participantiCursa.IdSofer = reader.GetInt32(1);
                participantiCursa.IdCursa = reader.GetInt32(2);
                return participantiCursa;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public IEnumerable<ParticipantiCursa> FindAll()
        {
            var connection = _utils.GetConnection();
            var commander =_utils.GetCommander(connection);
            try
            {
                List<ParticipantiCursa> participanti = new List<ParticipantiCursa>();
                connection.Open();
                var sql = "select * from participanticursa";
                commander = new MySqlCommand(sql, connection);

                MySqlDataReader reader = commander.ExecuteReader();
                while (reader.Read())
                {
                    ParticipantiCursa participant = new ParticipantiCursa(reader.GetInt32(1), reader.GetInt32(2));
                    participant.Id = reader.GetInt32(0);
                    participanti.Add(participant);
                }

                return participanti;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
        }

        public void Save(ParticipantiCursa entity)
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
                var sql = "Insert into participanticursa(idSofer,idCursa) values(@idSofer,@idCursa)";
                commander = new MySqlCommand(sql
                    , connection);
                commander.Parameters.AddWithValue("@idSofer", entity.IdSofer);
                commander.Parameters.AddWithValue("@idCursa", entity.IdCursa);
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

        public void Update(int id,ParticipantiCursa participantiCursa)
        {
            try
            {
                _validator.validate(participantiCursa);
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