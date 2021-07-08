package services;

import java.io.IOException;

public interface ParticipantiObserver {
    void participantiAdded(Integer data) throws IOException;

    void participantiAdded() throws IOException;
}
