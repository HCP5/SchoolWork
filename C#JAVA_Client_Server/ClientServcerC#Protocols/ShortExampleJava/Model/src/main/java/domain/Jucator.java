package domain;

public class Jucator extends Entitate<Integer>{
    Integer idJucator;
    String cuvantPrimit;
    Integer litereGhicite;
    Integer puncte;
    String litere;

    public Jucator() {
    }

    public Jucator(Integer idJucator, String cuvantPrimit, Integer litereGhicite,Integer puncte,String litere) {
        this.idJucator = idJucator;
        this.cuvantPrimit = cuvantPrimit;
        this.litereGhicite = litereGhicite;
        this.puncte=puncte;
        this.litere=litere;
    }

    public String getLitere() {
        return litere;
    }

    public void setLitere(String litere) {
        this.litere = litere;
    }

    public Integer getPuncte() {
        return puncte;
    }

    public void setPuncte(Integer puncte) {
        this.puncte = puncte;
    }

    public Integer getIdJucator() {
        return idJucator;
    }

    public void setIdJucator(Integer idJucator) {
        this.idJucator = idJucator;
    }

    public String getCuvantPrimit() {
        return cuvantPrimit;
    }

    public void setCuvantPrimit(String cuvantPrimit) {
        this.cuvantPrimit = cuvantPrimit;
    }

    public Integer getLitereGhicite() {
        return litereGhicite;
    }

    public void setLitereGhicite(Integer litereGhicite) {
        this.litereGhicite = litereGhicite;
    }
}
