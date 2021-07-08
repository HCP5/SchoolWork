package services;

import java.io.Serializable;
import java.rmi.Remote;

public interface Observer  extends Remote {
    void update() throws Exception;
    void gameStarted() throws Exception;
}
