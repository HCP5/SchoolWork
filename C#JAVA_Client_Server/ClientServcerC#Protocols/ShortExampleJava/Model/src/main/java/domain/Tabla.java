package domain;

public class Tabla extends Entitate<Integer>{
    private Integer id1;
    private Integer id2;
    private Integer id3;

    public Tabla() {
    }

    public Tabla(Integer id1, Integer id2, Integer id3) {
        this.id1 = id1;
        this.id2 = id2;
        this.id3 = id3;
    }

    public Integer getId1() {
        return id1;
    }

    public void setId1(Integer id1) {
        this.id1 = id1;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public Integer getId3() {
        return id3;
    }

    public void setId3(Integer id3) {
        this.id3 = id3;
    }
}
