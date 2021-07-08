package implementation;

import DTOS.CursaDTO;
import entities.domain.*;
import repository.Repository;
import services.IMasterService;
import services.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MasterService implements IMasterService {

    private final Repository<Cursa, Integer> repoCursa;
    private final Repository<Echipa, Integer> repoEchipa;
    private final Repository<Masina, Integer> repoMasina;
    private final Repository<Sofer, Integer> repoSofer;
    private final Repository<ParticipantiCursa, Integer> repoParticipant;
    private final Repository<Utilizator, Integer> repoUtilizator;

    List<Observer> listOfObserveri=new ArrayList<>();

    public MasterService(Repository<Cursa, Integer> repoCursa, Repository<Echipa, Integer> repoEchipa, Repository<Masina, Integer> repoMasina, Repository<ParticipantiCursa, Integer> repoParticipanti, Repository<Sofer, Integer> repoSofer, Repository<Utilizator, Integer> repoUtilizator) {
        this.repoCursa=repoCursa;
        this.repoEchipa=repoEchipa;
        this.repoMasina=repoMasina;
        this.repoSofer=repoSofer;
        this.repoParticipant=repoParticipanti;
        this.repoUtilizator=repoUtilizator;
    }

    @Override
    public void logMeIn(String name, String password) throws Exception {
        List<Utilizator> listaUtilizatori= (List<Utilizator>) repoUtilizator.findAll();
        AtomicInteger ok= new AtomicInteger(0);
        listaUtilizatori.forEach(utilizator -> {
            if(utilizator.getNumeUtilizator().equals(name)&&utilizator.getPassWord().equals(password))
            {
               ok.getAndIncrement();
            }
        });
        if(ok.get()==0)
            throw new Exception("PAROLA SI USER INVALID!");
    }

    @Override
    public void addObserver(Observer client) throws Exception {
        try {
            listOfObserveri.add(client);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("AlreadyExists!");
        }
    }

    @Override
    public List<CursaDTO> getCurse() throws Exception {
        try {
            List<Cursa> listeCurse= StreamSupport.stream(repoCursa.findAll().spliterator(),false).collect(Collectors.toList());
            List<ParticipantiCursa> listaParticipanti= StreamSupport.stream(repoParticipant.findAll().spliterator(),false).collect(Collectors.toList());
            List<CursaDTO> listaCurse=new ArrayList<>();
            for (Cursa cursa : listeCurse) {
                int i=0;
                for (ParticipantiCursa participantiCursa : listaParticipanti) {
                    if(cursa.getId().equals(participantiCursa.getIdCursa())){
                        ++i;
                    }
                }
                listaCurse.add(new CursaDTO(cursa,i));
            }
            return listaCurse;
        } catch (Exception e) {
            throw new Exception("Imposibil de oferit");
        }
    }

    @Override
    public List<Sofer> getSoferi(Integer id) throws Exception {
        List<Sofer> rez=new ArrayList<>();
        try {
            List<ParticipantiCursa> listaParticipanti=StreamSupport.stream(repoParticipant.findAll().spliterator(),false).collect(Collectors.toList());
            List<Sofer> listaSoferi=StreamSupport.stream(repoSofer.findAll().spliterator(),false).collect(Collectors.toList());
            listaParticipanti.forEach(participantiCursa -> {
                if(participantiCursa.getIdCursa().equals(id)){
                    rez.add(listaSoferi.stream().filter(sofer -> sofer.getId().equals(participantiCursa.getIdSofer())).findAny().orElse(null));
                }
            });
            return rez;
        } catch (Exception e) {
            throw new Exception("Cursa sau soferi inexistenti!");
        }
    }

    @Override
    public List<Echipa> getEchipe() throws Exception {
        try {
            List<Echipa> listaEchipe=StreamSupport.stream(repoEchipa.findAll().spliterator(),false).collect(Collectors.toList());
            return listaEchipe;
        } catch (Exception e) {
            throw new Exception("Imposibil de preluat echipe");
        }
    }

    @Override
    public List<Sofer> getSoferi(Echipa echipa1) throws Exception {
        List<Sofer> rez=new ArrayList<>();
        int idEchipa=echipa1.getId();
        try {
            List<Sofer> listaSoferi=StreamSupport.stream(repoSofer.findAll().spliterator(),false).collect(Collectors.toList());
            if(!listaSoferi.isEmpty())
                listaSoferi.forEach(sofer -> {
                    if(sofer.getIdEchipa().equals(idEchipa))
                        rez.add(sofer);
                });
            return rez;
        } catch (Exception e) {
            throw new Exception("Nu Putem oferi staff");
        }
    }

    @Override
    public void logMeOut(Observer userController) throws Exception {
        try {
            listOfObserveri.remove(userController);
        } catch (Exception e) {
            throw new Exception("Can't log you out!");
        }
    }

    @Override
    public void addParticipant(String numeSofer, Echipa echipaDorita, String nrMasina, Cursa cursaSelectata) throws Exception {
        try {
            Masina masina=new Masina(nrMasina,cursaSelectata.getCapacitateMotor());
            repoMasina.save(masina);
            List<Masina> listaMasini=StreamSupport.stream(repoMasina.findAll().spliterator(),false).collect(Collectors.toList());
            listaMasini.forEach(masina1 -> {
                    masina.setId(masina1.getId());
            });
            Sofer sofer=new Sofer(numeSofer,echipaDorita.getId(),masina.getId());
            repoSofer.save(sofer);
            List<Sofer> listaSofer=StreamSupport.stream(repoSofer.findAll().spliterator(),false).collect(Collectors.toList());
            listaSofer.forEach(sofer1 -> {
                    sofer.setId(sofer1.getId());
            });
            repoParticipant.save(new ParticipantiCursa(sofer.getId(),cursaSelectata.getId()));

            ExecutorService executorService= Executors.newFixedThreadPool(listOfObserveri.size());
            for (Observer observer : listOfObserveri) {
                executorService.execute(() -> {
                    try {
                        observer.update();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (Exception e) {
            throw new Exception("Imposibil!");
        }
    }
}
