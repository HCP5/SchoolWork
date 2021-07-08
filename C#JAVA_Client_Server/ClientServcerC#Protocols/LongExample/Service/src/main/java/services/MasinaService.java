package services;


import entities.domain.Masina;

import java.util.List;

public interface MasinaService {
    public List<Masina> getCars() throws Exception;
    public Masina findCar(Integer idMasina) throws Exception;
    public void addCar(Masina masina) throws Exception;
}
