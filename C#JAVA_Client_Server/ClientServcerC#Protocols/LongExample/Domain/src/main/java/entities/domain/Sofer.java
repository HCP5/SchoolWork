package entities.domain;

public class Sofer extends Entitate<Integer> {
    String numeSofer;
    Integer idEchipa;
    Integer idMasina;

    @Override
    public String toString() {
        return "entities.domain.Sofer{" +
                "numeSofer='" + numeSofer + '\'' +
                ", idEchipa=" + idEchipa +
                ", idMasina=" + idMasina +
                '}';
    }

    public String getNumeSofer() {
        return numeSofer;
    }

    public void setNumeSofer(String numeSofer) {
        this.numeSofer = numeSofer;
    }

    public Integer getIdEchipa() {
        return idEchipa;
    }

    public void setIdEchipa(Integer idEchipa) {
        this.idEchipa = idEchipa;
    }

    public Integer getIdMasina() {
        return idMasina;
    }

    public void setIdMasina(Integer idMasina) {
        this.idMasina = idMasina;
    }

    public Sofer(String numeSofer, Integer idEchipa, Integer idMasina) {
        this.numeSofer = numeSofer;
        this.idEchipa = idEchipa;
        this.idMasina = idMasina;
    }
}
