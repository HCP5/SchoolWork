package services;


import entities.domain.Sofer;

import java.util.List;

public interface SoferService {
    public List<Sofer> getRacers() throws Exception;
    public Sofer findRacer(Integer idSofer) throws Exception;
    public void addSofer(Sofer sofer) throws Exception;
}
