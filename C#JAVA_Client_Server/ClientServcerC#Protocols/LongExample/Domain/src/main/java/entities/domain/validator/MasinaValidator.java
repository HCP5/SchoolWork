package entities.domain.validator;


import entities.domain.Masina;
import entities.domain.validator.interfaceAndExceptions.ValidationException;
import entities.domain.validator.interfaceAndExceptions.Validator;

public class MasinaValidator implements Validator<Masina> {
    @Override
    public void validate(Masina entity) throws ValidationException {
        if(entity.getNrMasina().length()==0 || entity.getCapacitateMotor()==null){
            throw new ValidationException("entities.domain.Masina INVALIDA");
        }
    }
}
