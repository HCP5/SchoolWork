package entities.domain.validator;


import entities.domain.Sofer;
import entities.domain.validator.interfaceAndExceptions.ValidationException;
import entities.domain.validator.interfaceAndExceptions.Validator;

public class SoferValidator implements Validator<Sofer> {
    @Override
    public void validate(Sofer entity) throws ValidationException {
        if(entity.getNumeSofer().length()==0 || entity.getIdEchipa()==null || entity.getIdMasina()==null){
            throw new ValidationException("entities.domain.Sofer INVALID!");
        }
    }
}
