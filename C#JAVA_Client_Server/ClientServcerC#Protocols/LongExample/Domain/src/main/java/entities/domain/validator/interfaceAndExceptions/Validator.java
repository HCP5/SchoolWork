package entities.domain.validator.interfaceAndExceptions;

public interface Validator <T>{
    void validate(T entity) throws ValidationException;
}
