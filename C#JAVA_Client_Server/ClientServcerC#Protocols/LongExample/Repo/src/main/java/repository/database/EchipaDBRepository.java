package repository.database;


import entities.domain.Echipa;
import entities.domain.validator.interfaceAndExceptions.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import repository.EchipaRepository;
import repository.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EchipaDBRepository implements EchipaRepository {
    private Validator<Echipa> validator;

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger(EchipaDBRepository.class);

    public EchipaDBRepository(Properties props, Validator<Echipa> validator) {
        this.validator=validator;
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        this.dbUtils =new JdbcUtils(props);    }

    @Override
    public Echipa findOne(Integer integer) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from echipa where idEchipa=?"))
        {
            preStmt.setInt(1,integer);
            try(ResultSet resultSet=preStmt.executeQuery()){
                resultSet.next();
                int id=resultSet.getInt("idEchipa");
                String numeEchipa=resultSet.getString("numeEchipa");
                Echipa echipa=new Echipa(numeEchipa);
                echipa.setId(id);
                return echipa;
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
    public Iterable<Echipa> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Echipa> echipe=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from echipa"))
        {
            try(ResultSet resultSet=preStmt.executeQuery()){
                while (resultSet.next()){
                    int id=resultSet.getInt("idEchipa");
                    String numeEchipa=resultSet.getString("numeEchipa");
                    Echipa echipa=new Echipa(numeEchipa);
                    echipa.setId(id);
                    echipe.add(echipa);
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
        logger.traceExit(echipe);
        return echipe;
    }

    @Override
    public void save(Echipa echipa) {
        logger.traceEntry("saving task {}",echipa);
        try{
            validator.validate(echipa);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("insert into echipa (numeEchipa)values (?)")){
            preparedStatement.setString(1,echipa.getNumeEchipa());
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
    public void update(Integer integer,Echipa echipa) {
        logger.traceEntry("update task {}",integer);
        try{
            validator.validate(echipa);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("update echipa set numeEchipa=? where idEchipa=?")){
            preparedStatement.setInt(1,integer);
            preparedStatement.setString(2,"Merge");
            int result=preparedStatement.executeUpdate();
            logger.trace("Updated {} instance",result);
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
        try (PreparedStatement preparedStatement=con.prepareStatement("delete from echipa where idEchipa=?")){
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
