package repository;

import entities.domain.Entitate;

public interface Repository<E extends Entitate<ID>,ID>{
    /**
     *
     * @param id
     * @return Entitatea cautata
     */
    E findOne(ID id);

    /**
     *
     * @return Iterable, toate entitatiile
     */
    Iterable<E> findAll();

    void save(E elem);
    void update(ID id,E elem);
    void delete(ID id);
}
