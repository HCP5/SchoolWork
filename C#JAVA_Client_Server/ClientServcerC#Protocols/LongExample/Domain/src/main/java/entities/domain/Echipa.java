package entities.domain;

public class Echipa extends Entitate<Integer> {
    String numeEchipa;

    public Echipa(String numeEchipa) {
        this.numeEchipa=numeEchipa;
    }

    @Override
    public String toString() {
        return numeEchipa;
    }

    public String getNumeEchipa() {
        return numeEchipa;
    }

    public void setNumeEchipa(String numeEchipa) {
        this.numeEchipa = numeEchipa;
    }


}
