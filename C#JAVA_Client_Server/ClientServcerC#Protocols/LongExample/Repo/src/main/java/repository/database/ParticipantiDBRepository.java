package repository.database;

import entities.domain.ParticipantiCursa;
import entities.domain.validator.interfaceAndExceptions.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.JdbcUtils;
import repository.ParticipantiCursaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantiDBRepository implements ParticipantiCursaRepository {
    private Validator<ParticipantiCursa> validator;

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger(ParticipantiDBRepository.class);

    public ParticipantiDBRepository(Properties props,Validator<ParticipantiCursa> validator) {
        this.validator=validator;
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public ParticipantiCursa findOne(Integer integer) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from participanticursa where idParticipant=?"))
        {
            preStmt.setInt(1,integer);
            try(ResultSet resultSet=preStmt.executeQuery()){
                resultSet.next();
                int id1=resultSet.getInt("idSofer");
                int id2=resultSet.getInt("idCursa");

                ParticipantiCursa participantiCursa=new ParticipantiCursa(id1,id2);
                participantiCursa.setId(resultSet.getInt("idParticipant"));
                return participantiCursa;
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        logger.traceExit(integer);
        return null;
    }

    @Override
    public Iterable<ParticipantiCursa> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<ParticipantiCursa> participantiCurse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from participanticursa"))
        {
            try(ResultSet resultSet=preStmt.executeQuery()){
                while (resultSet.next()){
                    int id=resultSet.getInt("idParticipant");
                    Integer idSofer=resultSet.getInt("idSofer");
                    Integer idCursa=resultSet.getInt("idCursa");
                    ParticipantiCursa participantiCursa=new ParticipantiCursa(idSofer,idCursa);
                    participantiCursa.setId(id);
                    participantiCurse.add(participantiCursa);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        logger.traceExit(participantiCurse);
        return participantiCurse;
    }

    @Override
    public void save(ParticipantiCursa participantiCursa) {
        logger.traceEntry("saving task {}",participantiCursa);
        try{
            validator.validate(participantiCursa);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("insert into participanticursa (idSofer,idCursa)values (?,?)")){
            preparedStatement.setInt(1,participantiCursa.getIdSofer());
            preparedStatement.setInt(2,participantiCursa.getIdCursa());
            int result=preparedStatement.executeUpdate();
            logger.trace("Saved {} instance",result);
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer,ParticipantiCursa participantiCursa) {
        logger.traceEntry("update task {}",integer);
        try{
            validator.validate(participantiCursa);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("update participanticursa set nrMasina=? where idParticipant=?")){
            preparedStatement.setInt(2,participantiCursa.getIdSofer());
            preparedStatement.setInt(3,participantiCursa.getIdCursa());
            preparedStatement.setInt(1,integer);
            int result=preparedStatement.executeUpdate();
            logger.trace("updated {} instance",result);
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("delete task {}",integer);
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("delete from participanticursa where idParticipant=?")){
            preparedStatement.setInt(1,integer);
            int result=preparedStatement.executeUpdate();
            logger.trace("deleted {} instance",result);
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        logger.traceExit();
    }
}
