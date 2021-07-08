package services;


import entities.domain.Cursa;

import java.util.List;

public interface CursaService {
    public List<Cursa> getRaces() throws Exception;
    public Cursa getRace(Integer idCursa) throws Exception;
}
