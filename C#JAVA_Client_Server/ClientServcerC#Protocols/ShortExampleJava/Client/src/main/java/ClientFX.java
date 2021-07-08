import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.IMasterService;

public class ClientFX extends Application {
    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IMasterService server=(IMasterService) factory.getBean("examService");
            System.out.println("Obtained a reference to remote chat server");

            FXMLLoader loader=new FXMLLoader(getClass().getResource("loginView.fxml"));
            Parent root=loader.load();
            LoginController controller=loader.getController();
            controller.setService(server);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Chat Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
