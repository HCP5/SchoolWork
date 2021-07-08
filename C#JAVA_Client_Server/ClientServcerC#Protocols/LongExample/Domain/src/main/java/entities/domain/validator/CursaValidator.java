package entities.domain.validator;


import entities.domain.Cursa;
import entities.domain.validator.interfaceAndExceptions.ValidationException;
import entities.domain.validator.interfaceAndExceptions.Validator;

public class CursaValidator implements Validator<Cursa> {
    @Override
    public void validate(Cursa entity) throws ValidationException {
        if(entity.getCapacitateMotor()==null||entity.getNumeCupa().length()==0){
            throw new ValidationException("entities.domain.Cursa invalida");
        }
    }
}
