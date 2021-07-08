package services;

import DTOS.CursaDTO;
import entities.domain.Cursa;
import entities.domain.Echipa;
import entities.domain.Sofer;

import java.rmi.Remote;
import java.util.List;

public interface IMasterService extends Remote{
    void logMeIn(String name, String password) throws Exception;

    void addObserver(Observer client) throws Exception;

    List<CursaDTO> getCurse() throws Exception;

    List<Sofer> getSoferi(Integer id) throws Exception;

    List<Echipa> getEchipe() throws Exception;

    List<Sofer> getSoferi(Echipa echipa1) throws Exception;

    void logMeOut(Observer userController) throws Exception;

    void addParticipant(String numeSofer, Echipa echipaDorita, String nrMasina, Cursa cursaSelectata) throws Exception;

}
