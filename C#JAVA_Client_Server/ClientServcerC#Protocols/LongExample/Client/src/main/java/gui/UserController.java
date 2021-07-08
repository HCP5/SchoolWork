package gui;

import DTOS.CursaDTO;
import entities.domain.Cursa;
import entities.domain.Echipa;
import entities.domain.Sofer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.IMasterService;
import services.Observer;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserController extends UnicastRemoteObject implements Observer , Serializable {
    public TableView<TabelaCurseDTO> curseTable;
    public TableColumn<TabelaCurseDTO, String> numeCupaTC;
    public TableColumn<TabelaCurseDTO, Integer> capacitateTC;
    public TableColumn<TabelaCurseDTO, Integer> participantiTC;
    public Button logOutButton;
    public ComboBox<Echipa> echipa;
    public TableView<SoferMotor> SoferMotorT;
    public TableColumn<SoferMotor, String> soferTC;
    public TableColumn<SoferMotor, Integer> motorTC;
    public Button inscrie;
    public Scene parentScene;
    public IMasterService server;

    ObservableList<TabelaCurseDTO> curseDTOS= FXCollections.observableArrayList();
    ObservableList<SoferMotor> soferDTOS= FXCollections.observableArrayList();
    private List<Echipa> listaEchipe;

    public UserController() throws RemoteException {
    }

    public void getParticipanti(MouseEvent mouseEvent) {
        soferDTOS.removeAll();
        SoferMotorT.getItems().clear();
        TabelaCurseDTO cursaDorita=curseTable.getSelectionModel().getSelectedItem();
        try {
            int capacitate=cursaDorita.getCapacitate();
            List<Sofer> listaSoferi=server.getSoferi(cursaDorita.getCursa().getId());
            listaSoferi.forEach(sofer -> {
                SoferMotor soferDeAdaugat=new SoferMotor(sofer,capacitate);
                soferDTOS.add(soferDeAdaugat);
            });

            soferTC.setCellValueFactory(new PropertyValueFactory<SoferMotor,String>("sofer"));
            motorTC.setCellValueFactory(new PropertyValueFactory<SoferMotor,Integer>("capacitateMotor"));

            SoferMotorT.setItems(soferDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logMeOut(ActionEvent actionEvent) throws Exception {
        server.logMeOut(this);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Stage steage=new Stage();
        steage.setScene(parentScene);
        steage.show();
    }

    public void getThisTeam(ActionEvent actionEvent) throws Exception {
        Echipa echipa1=echipa.getSelectionModel().getSelectedItem();
        List<Sofer> soferi=server.getSoferi(echipa1);
        SoferMotorT.getItems().clear();
        soferTC.setCellValueFactory(new PropertyValueFactory<SoferMotor,String>("sofer"));
        motorTC.setCellValueFactory(new PropertyValueFactory<SoferMotor,Integer>("capacitateMotor"));

        soferDTOS.clear();
        soferi.forEach(sofer -> {
            soferDTOS.add(new SoferMotor(sofer,1000));
        });

        SoferMotorT.setItems(soferDTOS);
    }

    public void goToInscrie(ActionEvent actionEvent) throws IOException {
        if(curseTable.getSelectionModel().getSelectedItem()!=null){
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/addRacerView.fxml"));
            Parent root=loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            AddRaceController controller=loader.getController();
            controller.setEchipe(this.listaEchipe);
            controller.setCursa(curseTable.getSelectionModel().getSelectedItem().getCursa());
            controller.setService(this.server);
            stage.show();
        }
    }

    public void closeWindow() throws Exception {
        server.logMeOut(this);
        Stage steage=new Stage();
        steage.setScene(parentScene);
        steage.show();
    }

    public void setParent(Scene scene) {
        this.parentScene=scene;
    }

    public void setService(IMasterService server) throws Exception {
        curseTable.getItems().clear();
        this.server=server;
        List<CursaDTO> lista=server.getCurse();
        numeCupaTC.setCellValueFactory(new PropertyValueFactory<TabelaCurseDTO,String>("numeCupa"));
        capacitateTC.setCellValueFactory(new PropertyValueFactory<TabelaCurseDTO, Integer>("capacitate"));
        participantiTC.setCellValueFactory(new PropertyValueFactory<TabelaCurseDTO, Integer>("nrParticipanti"));

        lista.forEach(cursaDTO -> {
            curseDTOS.add(new TabelaCurseDTO(cursaDTO.getCursa(),cursaDTO.getNrParticipanti()));
        });

        List<Echipa> listaEchipe=server.getEchipe();
        this.listaEchipe=listaEchipe;
        ObservableList<Echipa> obs=FXCollections.observableArrayList();
        obs.addAll(listaEchipe);
        echipa.setItems(obs);
        curseTable.setItems(curseDTOS);

    }

    @Override
    public void update() throws Exception {
        setService(this.server);
    }

    public class TabelaCurseDTO{
        public Cursa cursa;
        public int nrParticipanti;

        public TabelaCurseDTO(Cursa cursa, int nrParticipanti) {
            this.cursa = cursa;
            this.nrParticipanti = nrParticipanti;
        }

        public String getNumeCupa(){
            return cursa.getNumeCupa();
        }

        public int getCapacitate(){
            return cursa.getCapacitateMotor();
        }

        public int getNrParticipanti(){
            return nrParticipanti;
        }

        public Cursa getCursa() {
            return cursa;
        }
    }

    public class SoferMotor {
        Sofer sofer;
        int capacitateMotor;

        public SoferMotor(Sofer sofer, int capacitate) {
            this.sofer=sofer;
            this.capacitateMotor=capacitate;
        }

        public String getSofer(){
            String id=sofer.getId().toString();
            return id+" "+sofer.getNumeSofer();
        }
        public int getCapacitateMotor(){
            return capacitateMotor;
        }
    }
}
