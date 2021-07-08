package controllers;

import domain.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import services.IMasterService;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginController extends UnicastRemoteObject {
    public TextField usernameField;
    public PasswordField passedField;
    public Button logInButton;
    private IMasterService server;

    public LoginController() throws RemoteException {
    }

    public void setService(IMasterService server) {
        this.server=server;
    }

    public void logMeIn(ActionEvent actionEvent) throws Exception {
        String user=usernameField.getText();
        String passwd=passedField.getText();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/userview.fxml"));
        Parent root=loader.load();
        UserController controller=loader.getController();
        Stage stage=new Stage();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    controller.closeWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        controller.setParent(((Node)(actionEvent.getSource())).getScene());
        stage.setScene(new Scene(root));

        controller.setService(server);
        User user1=server.logMeIn(user, passwd, controller);
        if( user1!= null){
            controller.setUser(user1);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Notificare!");
            alert.setContentText("Connectare nereusita");
            alert.showAndWait();
        }
    }
}
