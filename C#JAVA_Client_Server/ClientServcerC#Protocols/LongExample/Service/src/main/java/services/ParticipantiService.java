package services;

import entities.domain.ParticipantiCursa;

import java.util.List;

public interface ParticipantiService {
    public List<ParticipantiCursa> getParticipanti() throws Exception;
    public void addParticipant(ParticipantiCursa participantiCursa) throws Exception;
}
