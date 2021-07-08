package repository.database;

import entities.domain.Utilizator;
import entities.domain.validator.interfaceAndExceptions.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import repository.JdbcUtils;
import repository.UtilizatorRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UtilizatorDBRepository implements UtilizatorRepository {
    private Validator<Utilizator> validator;

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger(UtilizatorDBRepository.class);

    public UtilizatorDBRepository(Properties props,Validator<Utilizator> validator) {
        this.validator=validator;
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public Utilizator findOne(Integer integer) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from utilizator where idUtilizator=?"))
        {
            preStmt.setInt(1,integer);
            try(ResultSet resultSet=preStmt.executeQuery()){
                resultSet.next();
                int id=resultSet.getInt("idUtilizator");
                String nume=resultSet.getString("user");

                Utilizator utilizator=new Utilizator(nume);
                utilizator.setId(id);
                return utilizator;
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
    public Iterable<Utilizator> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Utilizator> utilizators=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from utilizator"))
        {
            try(ResultSet resultSet=preStmt.executeQuery()){
                while (resultSet.next()){
                    int id=resultSet.getInt("idUtilizator");
                    String user=resultSet.getString("user");
                    String password=resultSet.getString("password");
                    Utilizator utilizator=new Utilizator(user,password);
                    utilizator.setId(id);
                    utilizators.add(utilizator);
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
        logger.traceExit(utilizators);
        return utilizators;
    }

    @Override
    public void save(Utilizator utilizator) {
        logger.traceEntry("saving task {}",utilizator);
        try{
            validator.validate(utilizator);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("insert into utilizator (user) values (?)")){

            preparedStatement.setString(1,utilizator.getNumeUtilizator());
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
    public void update(Integer integer,Utilizator utilizator) {
        logger.traceEntry("update task {}",integer);
        try{
            validator.validate(utilizator);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("update utilizator set user=? where idCursa=?")){
            preparedStatement.setString(2,"Horatiu");
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
        try (PreparedStatement preparedStatement=con.prepareStatement("delete from utilizator where idUtilizator=?")){
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
