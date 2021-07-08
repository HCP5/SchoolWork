package entities.domain.validator;


import entities.domain.Echipa;
import entities.domain.validator.interfaceAndExceptions.ValidationException;
import entities.domain.validator.interfaceAndExceptions.Validator;

public class EchipaValidator implements Validator<Echipa> {
    @Override
    public void validate(Echipa entity) throws ValidationException {
        if(entity.getNumeEchipa().length()==0){
            throw new ValidationException("entities.domain.Echipa INVALIDA");
        }
    }
}
