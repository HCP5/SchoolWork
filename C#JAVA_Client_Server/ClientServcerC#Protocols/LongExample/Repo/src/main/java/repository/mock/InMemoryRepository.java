package repository.mock;

import entities.domain.Entitate;
import entities.domain.validator.interfaceAndExceptions.Validator;
import repository.Repository;

import java.util.HashMap;
import java.util.Map;


public class InMemoryRepository<ID, E extends Entitate<ID>> implements Repository<E,ID> {

    private Validator<E> validator;
    Map<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public void save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");
        try{
            validator.validate(entity);
        }catch (Exception e){
            System.out.println(e);
        }
        entities.putIfAbsent(entity.getId(), entity);
    }

    @Override
    public void delete(ID id) {
        if (entities.get(id)!=null){
            entities.remove(id);
        }
    }

    @Override
    public void update(ID id,E entity) {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        try{
            validator.validate(entity);
        }catch (Exception e){
            System.out.println(e);
            return;
        }

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return;
        }
    }

}
