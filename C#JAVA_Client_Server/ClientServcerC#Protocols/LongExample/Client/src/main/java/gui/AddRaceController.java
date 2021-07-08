package gui;

import entities.domain.Cursa;
import entities.domain.Echipa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.IMasterService;

import java.util.List;

public class AddRaceController {
    public Label labelNumeCupa;
    public Label labelCapacitate;
    public TextField numeTextBox;
    public ComboBox<Echipa> comboEchipe;
    public TextField nrMaxinaTextBox;
    public Button addParticipant;
    private List<Echipa> echipe;
    private Cursa cursaSelectata;
    private IMasterService server;

    public void addThis(ActionEvent actionEvent) throws Exception {
        server.addParticipant(numeTextBox.getText(),comboEchipe.getSelectionModel().getSelectedItem(),nrMaxinaTextBox.getText(),cursaSelectata);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void setEchipe(List<Echipa> listaEchipe) {
        this.echipe=listaEchipe;
    }

    public void setCursa(Cursa selectedItem) {
        this.cursaSelectata=selectedItem;
    }

    public void setService(IMasterService server) {
        this.server=server;
        labelNumeCupa.setText(cursaSelectata.getNumeCupa());
        labelCapacitate.setText(cursaSelectata.getCapacitateMotor().toString());
        ObservableList<Echipa> obs= FXCollections.observableArrayList();
        obs.addAll(echipe);
        comboEchipe.setItems(obs);
    }
}
