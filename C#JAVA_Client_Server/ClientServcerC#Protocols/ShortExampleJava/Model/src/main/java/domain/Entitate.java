package domain;

import java.io.Serializable;

public abstract class Entitate<ID> implements Serializable {
    public Entitate() {
    }

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
