package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Message;
import co.usa.ciclo3.reto5.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository MessageRepository;

    public List<Message> getAll() {
        return MessageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return MessageRepository.getMessage(id);
    }

    public Message save(Message p) {
        if (p.getIdMessage() == null) {
            return MessageRepository.save(p);
        } else {
            Optional<Message> paux = MessageRepository.getMessage(p.getIdMessage());
            if (paux.isEmpty()) {
                return MessageRepository.save(p);
            } else {
                return p;
            }
        }
    }

    public Message update(Message c) {
        if (c.getIdMessage() != null) {
            Optional<Message> g = MessageRepository.getMessage(c.getIdMessage());
            if (!g.isEmpty()) {
                if (c.getMessageText() != null) {
                    g.get().setMessageText(c.getMessageText());
                }
                if (c.getSkate() != null) {
                    g.get().setSkate(c.getSkate());
                }
                if (c.getClient()!= null) {
                    g.get().setClient(c.getClient());
                }
                return MessageRepository.save(g.get());
            }
        }
        return c;
    }

    public boolean deleteMessage(int id) {
        Boolean d = getMessage(id).map(Message -> {
            MessageRepository.delete(Message);
            return true;
        }).orElse(false);
        return d;
    }

}
