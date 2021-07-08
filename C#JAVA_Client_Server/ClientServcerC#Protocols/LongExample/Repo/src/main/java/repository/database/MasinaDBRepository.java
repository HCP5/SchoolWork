package repository.database;


import entities.domain.Masina;
import entities.domain.validator.interfaceAndExceptions.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import repository.JdbcUtils;
import repository.MasinaRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MasinaDBRepository implements MasinaRepository {
    private Validator<Masina> validator;

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger(MasinaDBRepository.class);

    public MasinaDBRepository(Properties props,Validator<Masina> validator) {
        this.validator=validator;
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public Masina findOne(Integer integer) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from masina where idMasina=?"))
        {
            preStmt.setInt(1,integer);
            try(ResultSet resultSet=preStmt.executeQuery()){
                resultSet.next();
                int id=resultSet.getInt("idMasina");
                String nrMasina=resultSet.getString("nrMasina");
                int capacitateMotor=resultSet.getInt("capacitateMotor");
                Masina masina=new Masina(nrMasina,capacitateMotor);
                masina.setId(id);
                return masina;
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
    public Iterable<Masina> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Masina> masini=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from masina"))
        {
            try(ResultSet resultSet=preStmt.executeQuery()){
                while (resultSet.next()){
                    int id=resultSet.getInt("idMasina");
                    String nrMasina=resultSet.getString("nrMasina");
                    int capacitateMotor=resultSet.getInt("capacitateMotor");
                    Masina masina=new Masina(nrMasina,capacitateMotor);
                    masina.setId(id);
                    masini.add(masina);
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
        logger.traceExit(masini);
        return masini;
    }

    @Override
    public void save(Masina masina) {
        logger.traceEntry("saving task {}",masina);
        try{
            validator.validate(masina);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("insert into masina (nrMasina,capacitateMotor)values (?,?)")){
            preparedStatement.setString(1,masina.getNrMasina());
            preparedStatement.setInt(2,masina.getCapacitateMotor());
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
    public void update(Integer integer,Masina masina) {
        logger.traceEntry("update task {}",integer);
        try{
            validator.validate(masina);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("update masina set nrMasina=? where idCursa=?")){
            preparedStatement.setString(2,"2000");
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
        try (PreparedStatement preparedStatement=con.prepareStatement("delete from masina where idMasina=?")){
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
