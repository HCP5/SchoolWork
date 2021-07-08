package impl;

import domain.Jucator;
import domain.User;
import hibernatesRepo.JucatorRepo;
import hibernatesRepo.UserRepo;
import services.IMasterService;
import services.Observer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MasterService implements IMasterService{
    Map<Integer,Observer> listObserveri=new ConcurrentHashMap<>();
    UserRepo userRepo;
    JucatorRepo jucatorRepo;

    public MasterService(UserRepo userRepo,JucatorRepo repoJucator) {
        this.userRepo = userRepo;
        this.jucatorRepo=repoJucator;
    }

    @Override
    public User logMeIn(String user, String passwd,Observer observer) {
        List<User> listaUser=StreamSupport.stream(userRepo.findAll().spliterator(),false).collect(Collectors.toList());
        for (User user1 : listaUser) {
            if (user1.getUsername().equals(user) && user1.getPasswd().equals(passwd)) {
                listObserveri.put(user1.getId(), observer);
                if (listObserveri.size() != 0) {
                    ExecutorService executorService = Executors.newFixedThreadPool(listObserveri.size());

                    for (Integer id : listObserveri.keySet())
                        executorService.execute(() -> {
                            try {
                                listObserveri.get(id).update();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                }
                return user1;
            }
        }
        return null;
    }

    @Override
    public boolean logMeOut(User user) {
        if(listObserveri.containsKey(user.getId())) {
            System.out.println("INAINTE:");
            System.out.println(listObserveri.toString());
            listObserveri.remove(user.getId());
            System.out.println("Dupa:");
            System.out.println(listObserveri.toString());
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void StartGame(User curentUser,User userAles,String cuvantDat) throws Exception {
        if(listObserveri.size()==3){
            ExecutorService executorService= Executors.newFixedThreadPool(listObserveri.size());

            for(Integer id : listObserveri.keySet())
                    executorService.execute(()-> {
                        try {
                            listObserveri.get(id).gameStarted();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                            });
            jucatorRepo.save(new Jucator(curentUser.getId(),cuvantDat,0,0,""));
        }
        else throw new Exception("Not enough users");
    }

    @Override
    public List<User> getLogedUsers() {
        return StreamSupport.stream(userRepo.findAll().spliterator(),false).filter(
                user -> {
                    if(listObserveri.get(user.getId())!=null)
                        return true;
                    return false;
                }
        ).collect(Collectors.toList());
    }

}
