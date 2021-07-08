package entities.domain;

public class ParticipantiCursa extends Entitate<Integer> {
    Integer idSofer;
    Integer idCursa;

    @Override
    public String toString() {
        return "entities.domain.ParticipantiCursa{" +
                "idSofer=" + idSofer +
                ", idCursa=" + idCursa +
                '}';
    }

    public Integer getIdSofer() {
        return idSofer;
    }

    public void setIdSofer(Integer idSofer) {
        this.idSofer = idSofer;
    }

    public Integer getIdCursa() {
        return idCursa;
    }

    public void setIdCursa(Integer idCursa) {
        this.idCursa = idCursa;
    }

    public ParticipantiCursa(Integer idSofer, Integer idCursa) {
        this.idSofer = idSofer;
        this.idCursa = idCursa;
    }
}
