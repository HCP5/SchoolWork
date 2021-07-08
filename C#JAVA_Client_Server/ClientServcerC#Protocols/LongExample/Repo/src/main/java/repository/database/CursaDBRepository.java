package repository.database;

import entities.domain.Cursa;
import entities.domain.validator.interfaceAndExceptions.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import repository.CursaRepository;
import repository.JdbcUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CursaDBRepository implements CursaRepository {
    private Validator<Cursa> validator;

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger(CursaDBRepository.class);

    public CursaDBRepository(Properties props, Validator<Cursa> validator) {
        this.validator=validator;
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        this.dbUtils =new JdbcUtils(props);    }

    @Override
    public Cursa findOne(Integer integer) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from cursa where idCursa=?"))
        {
            preStmt.setInt(1,integer);
            try(ResultSet resultSet=preStmt.executeQuery()){
                resultSet.next();
                int id=resultSet.getInt("idCursa");
                int capacitateMotor=resultSet.getInt("capacitateMotor");
                String numeCupa=resultSet.getString("numeCupa");
                Cursa cursa=new Cursa(numeCupa,capacitateMotor);
                cursa.setId(id);
                return cursa;
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
    public Iterable<Cursa> findAll(){
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Cursa> curse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from cursa"))
        {
            try(ResultSet resultSet=preStmt.executeQuery()){
                while (resultSet.next()){
                    int id=resultSet.getInt("idCursa");
                    Integer capacitateMotor=resultSet.getInt("capacitateMotor");
                    String numeCupa=resultSet.getString("numeCupa");
                    Cursa cursa=new Cursa(numeCupa,capacitateMotor);
                    cursa.setId(id);
                    curse.add(cursa);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(curse);
        try {
            con.close();
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.err.println("ERROR DB"+throwables);
        }
        return curse;
    }

    @Override
    public void save(Cursa cursa) {
        logger.traceEntry("saving task {}",cursa);
        try{
            validator.validate(cursa);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("insert into cursa (capacitateMotor,numeCupa)values (?,?)")){
            preparedStatement.setInt(1,cursa.getCapacitateMotor());
            preparedStatement.setString(2,cursa.getNumeCupa());
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
    public void update(Integer integer,Cursa cursa) {
        logger.traceEntry("update task {}",integer);
        try{
            validator.validate(cursa);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("update cursa set capacitateMotr=? where idCursa=?")){
            preparedStatement.setString(3,cursa.getNumeCupa());
            preparedStatement.setInt(2,cursa.getCapacitateMotor());
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
        try (PreparedStatement preparedStatement=con.prepareStatement("delete from cursa where idCursa=?")){
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
