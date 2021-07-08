package gui;

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
import services.Observer;

import java.io.Serializable;

public class LoginController  {
    public TextField userField;
    public Button logInButton;
    public PasswordField passwordField;
    private IMasterService server;

    public void LogMeIn(ActionEvent actionEvent) {
        String name =userField.getText();
        String password =passwordField.getText();
        try {
            server.logMeIn(name,password);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Notificare");
            alert.setContentText("Connectare cu succes!");
            alert.showAndWait();

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/userView.fxml"));
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
            server.addObserver(controller);
            controller.setService(server);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Notificare");
            alert.setContentText("Connectare nereusita: "+e);
            alert.showAndWait();
        }
    }

    public void setService(IMasterService server) {
        this.server=server;
    }

}
