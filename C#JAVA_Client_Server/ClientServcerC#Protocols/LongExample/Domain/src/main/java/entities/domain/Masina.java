package entities.domain;

public class Masina extends Entitate<Integer> {
    String nrMasina;
    Integer capacitateMotor;

    public Masina() {
    }

    @Override
    public String toString() {
        return "Masini{" +
                "nrMasina='" + nrMasina + '\'' +
                ", capacitateMotor=" + capacitateMotor +
                '}';
    }

    public String getNrMasina() {
        return nrMasina;
    }

    public void setNrMasina(String nrMasina) {
        this.nrMasina = nrMasina;
    }

    public Integer getCapacitateMotor() {
        return capacitateMotor;
    }

    public void setCapacitateMotor(Integer capacitateMotor) {
        this.capacitateMotor = capacitateMotor;
    }

    public Masina(String nrMasina, Integer capacitateMotor) {
        this.nrMasina = nrMasina;
        this.capacitateMotor = capacitateMotor;
    }
}
