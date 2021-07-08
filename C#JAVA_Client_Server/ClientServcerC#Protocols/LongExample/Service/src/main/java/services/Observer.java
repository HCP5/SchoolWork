package services;

import java.io.Serializable;
import java.rmi.Remote;

public interface Observer extends Remote, Serializable {
    void update() throws Exception;
}
