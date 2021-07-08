import entities.domain.*;
import entities.domain.validator.*;
import entities.domain.validator.interfaceAndExceptions.Validator;
import implementation.MasterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.Repository;
import repository.database.*;
import services.IMasterService;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class RMIServer {
    public static void main(String[] args) {
        ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:spring-server.xml");
    }
}
