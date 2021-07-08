package repository.database;

import entities.domain.Sofer;
import entities.domain.validator.interfaceAndExceptions.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.JdbcUtils;
import repository.SoferRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SoferDBRepository implements SoferRepository {
    private Validator<Sofer> validator;

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger(SoferDBRepository.class);

    public SoferDBRepository(Properties props, Validator<Sofer> validator) {
        this.validator=validator;
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public Sofer findOne(Integer integer) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from sofer where idSofer=?"))
        {
            preStmt.setInt(1,integer);
            try(ResultSet resultSet=preStmt.executeQuery()){
                resultSet.next();
                int id=resultSet.getInt("idSofer");
                String numeSofer=resultSet.getString("numeSofer");
                int idEchipa=resultSet.getInt("idEchipa");
                int idMasina=resultSet.getInt("idMasina");

                Sofer sofer=new Sofer(numeSofer,idEchipa,idMasina);
                sofer.setId(id);
                return sofer;
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
    public Iterable<Sofer> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Sofer> soferi=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from sofer"))
        {
            try(ResultSet resultSet=preStmt.executeQuery()){
                while (resultSet.next()){
                    int id=resultSet.getInt("idSofer");
                    String numeSofer=resultSet.getString("numeSofer");
                    int idEchipa=resultSet.getInt("idEchipa");
                    int idMasina=resultSet.getInt("idMasina");
                    Sofer sofer=new Sofer(numeSofer,idEchipa,idMasina);
                    sofer.setId(id);
                    soferi.add(sofer);
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
        logger.traceExit(soferi);
        return soferi;
    }

    @Override
    public void save(Sofer sofer) {
        logger.traceEntry("saving task {}",sofer);
        try{
            validator.validate(sofer);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("insert into sofer (numeSofer,idEchipa,idMasina)values (?,?,?)")){
            preparedStatement.setString(1,sofer.getNumeSofer());
            preparedStatement.setInt(2,sofer.getIdEchipa());
            preparedStatement.setInt(3,sofer.getIdMasina());

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
    public void update(Integer integer,Sofer sofer) {
        logger.traceEntry("update task {}",integer);
        try{
            validator.validate(sofer);
        }catch (Exception e){
            logger.error(e);
            System.err.println("ERROR DOMAIN"+e);
            logger.traceExit();
            return;
        }
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preparedStatement=con.prepareStatement("update sofer set numeSofer=? where idCursa=?")){
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
        try (PreparedStatement preparedStatement=con.prepareStatement("delete from sofer where idSofer=?")){
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
