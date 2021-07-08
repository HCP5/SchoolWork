package controllers;

import domain.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.IMasterService;
import services.Observer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserController extends UnicastRemoteObject implements Observer {
    public Button logOutButton;
    public Button StartJocButton;
    public TableView<User> jucatoriDisponibiliTable;
    public TableColumn<User,Integer> idJucatorColumn;
    public TableColumn<User,String> NumeJucatorColumn;
    public TextField cuvantPropField;
    public TextField inputCuvantField;
    public Button propuneButton;
    private IMasterService server;
    private User curentUser;
    private Scene parentScene;

    private boolean started=false;

    ObservableList<User> connectedUsers= FXCollections.observableArrayList();

    public UserController() throws RemoteException {
    }

    @Override
    public void update() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                idJucatorColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
                NumeJucatorColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
                try {

                    connectedUsers.setAll(server.getLogedUsers());
                }
                catch (Exception e){};
                jucatoriDisponibiliTable.setItems(connectedUsers);
            }
        });
    }

    @Override
    public void gameStarted() throws Exception {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Notificare!");
                alert.setContentText("GameStarted!");
                alert.showAndWait();
                started=true;
                inputCuvantField.setText("Puteti asigna cuvant");
            }
        });
    }

    public void closeWindow() throws Exception {
        server.logMeOut(curentUser);
        Stage steage=new Stage();
        steage.setScene(parentScene);
        steage.show();
    }

    public void setParent(Scene scene) {
        this.parentScene=scene;
    }

    public void setService(IMasterService server) {
        this.server=server;
    }

    public void setUser(User user1) {
        this.curentUser=user1;
    }

    public void logMeOut(ActionEvent actionEvent) throws Exception {
        server.logMeOut(curentUser);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Stage steage=new Stage();
        steage.setScene(parentScene);
        steage.show();
    }

    public void StartGame(ActionEvent actionEvent) throws Exception {
        try {
            if(jucatoriDisponibiliTable.getSelectionModel().getSelectedItem()!=null && inputCuvantField.getText()!="")
                server.StartGame(curentUser,jucatoriDisponibiliTable.getSelectionModel().getSelectedItem(),inputCuvantField.getText());
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Notificare!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void propuneCuvantPentru(ActionEvent actionEvent) {
    }
}
