package DTOS;

import entities.domain.Cursa;
import entities.domain.Entitate;

import java.util.List;

public class CursaDTO extends Entitate<Integer> {
    Cursa cursa;
    Integer nrParticipanti;

    public CursaDTO(Cursa cursa, Integer participanti) {
        this.cursa=cursa;
        this.nrParticipanti=participanti;
    }


    public Cursa getCursa() {
        return cursa;
    }

    public Integer getNrParticipanti() {
        return nrParticipanti;
    }
}
