package hibernatesRepo;

import domain.Entitate;

public interface Repository <E extends Entitate<ID>,ID>{
    E findOne(ID id);

    Iterable<E> findAll();

    void save(E elem);
    void update(ID id,E elem);
    void delete(ID id);

}
