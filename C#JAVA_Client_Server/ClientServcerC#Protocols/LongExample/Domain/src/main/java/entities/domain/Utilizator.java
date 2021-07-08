package entities.domain;

public class Utilizator extends Entitate<Integer> {
    String numeUtilizator;
    String passWord;

    @Override
    public String toString() {
        return "entities.domain.Utilizator{" +
                "numeUtilizator='" + numeUtilizator + '\'' +
                '}';
    }

    public Utilizator() {
    }

    public Utilizator(String numeUtilizator, String passWord) {
        this.numeUtilizator = numeUtilizator;
        this.passWord = passWord;
    }

    public Utilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
