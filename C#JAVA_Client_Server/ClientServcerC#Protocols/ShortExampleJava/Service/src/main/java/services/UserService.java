package services;

import domain.User;

import java.rmi.Remote;
import java.util.List;

public interface UserService extends Remote {
    public User logMeIn(String user,String passwd, Observer observer) throws Exception;
    public boolean logMeOut(User user) throws Exception;
    public void StartGame(User curentUser,User userSelectat,String cuvantDat) throws Exception;
    public List<User> getLogedUsers() throws Exception;
}
