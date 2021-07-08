package entities.domain.validator;


import entities.domain.Utilizator;
import entities.domain.validator.interfaceAndExceptions.ValidationException;
import entities.domain.validator.interfaceAndExceptions.Validator;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {
        if(entity.getNumeUtilizator().length()==0)
            throw new ValidationException("entities.domain.Utilizator invalid!");
    }
}
