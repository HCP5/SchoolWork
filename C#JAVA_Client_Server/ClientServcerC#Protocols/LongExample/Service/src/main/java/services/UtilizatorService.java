package services;

import entities.domain.Utilizator;

import java.io.IOException;
import java.util.List;

public interface UtilizatorService {
    public List<Utilizator> getUsers();

    void logIn(Utilizator utilizator,ParticipantiObserver participantiObserver) throws Exception;

    void logOut(ParticipantiObserver participant) throws Exception;

    void updateAll() throws IOException;
}
