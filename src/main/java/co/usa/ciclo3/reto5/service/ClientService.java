package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Client;
import co.usa.ciclo3.reto5.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }

    public Client save(Client p) {
        if (p.getIdClient() == null) {
            return clientRepository.save(p);
        } else {
            Optional<Client> paux = clientRepository.getClient(p.getIdClient());
            if (paux.isEmpty()) {
                return clientRepository.save(p);
            } else {
                return p;
            }
        }
    }

    public Client update(Client c) {
        if (c.getIdClient() != null) {
            Optional<Client> g = clientRepository.getClient(c.getIdClient());
            if (!g.isEmpty()) {
                if (c.getName() != null) {
                    g.get().setName(c.getName());
                }
                if (c.getEmail() != null) {
                    g.get().setEmail(c.getEmail());
                }
                if (c.getPassword() != null) {
                    g.get().setPassword(c.getPassword());
                }
                if (c.getAge()!= null) {
                    g.get().setAge(c.getAge());
                }
                return clientRepository.save(g.get());
            }
        }
        return c;
    }

    public boolean deleteClient(int id) {
        Boolean d = getClient(id).map(Client -> {
            clientRepository.delete(Client);
            return true;
        }).orElse(false);
        return d;
    }

}
