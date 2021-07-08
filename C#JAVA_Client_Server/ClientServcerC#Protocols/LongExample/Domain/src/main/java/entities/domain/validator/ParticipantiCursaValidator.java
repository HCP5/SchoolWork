package entities.domain.validator;


import entities.domain.ParticipantiCursa;
import entities.domain.validator.interfaceAndExceptions.ValidationException;
import entities.domain.validator.interfaceAndExceptions.Validator;

public class ParticipantiCursaValidator implements Validator<ParticipantiCursa> {
    @Override
    public void validate(ParticipantiCursa entity) throws ValidationException {
        if(entity.getIdCursa()==null || entity.getIdSofer()==null){
            throw new ValidationException("Participant INVALID");
        }
    }
}
