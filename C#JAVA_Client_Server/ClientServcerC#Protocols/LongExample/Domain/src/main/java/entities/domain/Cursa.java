package entities.domain;

public class Cursa extends Entitate<Integer> {
    String numeCupa;
    Integer capacitateMotor;

    public Cursa(String numeCupa, Integer capacitateMotor) {
        this.numeCupa = numeCupa;
        this.capacitateMotor = capacitateMotor;
    }

    public Cursa() {

    }

    @Override
    public String toString() {
        return "Curse{" +
                "numeCupa='" + numeCupa + '\'' +
                ", capacitateMotor=" + capacitateMotor +
                '}';
    }

    public String getNumeCupa() {
        return numeCupa;
    }

    public void setNumeCupa(String numeCupa) {
        this.numeCupa = numeCupa;
    }


    public Integer getCapacitateMotor() {
        return capacitateMotor;
    }

    public void setCapacitateMotor(Integer capacitateMotor) {
        this.capacitateMotor = capacitateMotor;
    }
}
