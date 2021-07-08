package service.controller;

import domain.Jucator;
import domain.Tabla;
import hibernatesRepo.HibernateTabla;
import hibernatesRepo.JucatorRepo;
import hibernatesRepo.UserRepo;
import hibernatesRepo.interfaces.IJucatorRepo;
import hibernatesRepo.interfaces.ITablaRepo;
import hibernatesRepo.interfaces.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//3.Un  serviciu  REST  care  permite  vizualizarea  cuvintelor  propuse  de  jucători  la  începutul  jocului pentru un anumit joc.
// 4.Un  serviciu  REST  care  pentru  un  anumit  joc  și  un  anumit  jucător  permite
// vizualizarea  celor  3 propuneri și punctajul obținut la fiecare runda.
@RestController
@RequestMapping("/exam")
public class RestExamController {
    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IJucatorRepo jucatorRepo;

    @Autowired
    private ITablaRepo tablaRepo;

    @RequestMapping(value="/{idGame}",method = RequestMethod.GET)
    public Iterable<String> getCuvinteInceput(@PathVariable Integer idGame){
        Tabla tabla=tablaRepo.findOne(idGame);
        Jucator jucator1= jucatorRepo.findOne(tabla.getId1());
        Jucator jucator2=jucatorRepo.findOne(tabla.getId2());
        Jucator jucator3=jucatorRepo.findOne(tabla.getId3());
        List<String> result=new ArrayList<>();
        result.add("Id: "+jucator1.getId().toString()+", Cuvant: "+jucator1.getCuvantPrimit());
        result.add("Id: "+jucator2.getId().toString()+", Cuvant: "+jucator2.getCuvantPrimit());
        result.add("Id: "+jucator3.getId().toString()+", Cuvant: "+jucator3.getCuvantPrimit());
        return result;
    }

    @RequestMapping(value = "/{idGame}/{idJucator}",method = RequestMethod.GET)
    public Iterable<String> getCuvinteParcurs(@PathVariable Integer idGame,@PathVariable Integer idJucator){
        Tabla tabla=tablaRepo.findOne(idGame);
        List<String> result=new ArrayList<>();
        Jucator jucator1= jucatorRepo.findOne(tabla.getId1());
        Jucator jucator2=jucatorRepo.findOne(tabla.getId2());
        Jucator jucator3=jucatorRepo.findOne(tabla.getId3());
        if(idJucator.equals(jucator1.getIdJucator())){
            return StreamSupport.stream(Arrays.stream(jucator1.getLitere().split(",")).spliterator(),false).collect(Collectors.toList());
        }else{
            if(idJucator.equals(jucator2.getIdJucator()))
            {
                return StreamSupport.stream(Arrays.stream(jucator2.getLitere().split(",")).spliterator(),false).collect(Collectors.toList());
            }
            else
            {
                return StreamSupport.stream(Arrays.stream(jucator3.getLitere().split(",")).spliterator(),false).collect(Collectors.toList());
            }
        }
    }

}
