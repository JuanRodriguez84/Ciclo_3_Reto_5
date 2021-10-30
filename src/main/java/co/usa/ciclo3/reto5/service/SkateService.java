package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Skate;
import co.usa.ciclo3.reto5.repository.SkateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkateService {
    @Autowired
    private SkateRepository skateRepository;

    public List<Skate> getAll(){
        return skateRepository.getAll();
    }

    public Optional<Skate> getSkate(int id){
        return skateRepository.getSkate(id);
    }

    public Skate save(Skate p){
        if(p.getId()==null){
            return skateRepository.save(p);
        }else{
            Optional<Skate> paux=skateRepository.getSkate(p.getId());
            if(paux.isEmpty()){
                return skateRepository.save(p);
            }else{
                return p;
            }
        }
    }

     public Skate update(Skate c) {
        if (c.getId()!= null) {
            Optional<Skate> g = skateRepository.getSkate(c.getId());
            if (!g.isEmpty()) {
                if (c.getName()!= null || c.getName()!= "") {
                    g.get().setName(c.getName());
                }
                if (c.getBrand()!= null) {
                    g.get().setBrand(c.getBrand());
                }
                if (c.getYear()!= null) {
                    g.get().setYear(c.getYear());
                }
                if (c.getDescription()!= null) {
                    g.get().setDescription(c.getDescription());
                }
                if (c.getCategory()!= null) {
                    g.get().setCategory(c.getCategory());
                }
                return skateRepository.save(g.get());
            }
        }
        return c;
    }

    public boolean deleteSkate(int id) {
        Boolean d = getSkate(id).map(Skate -> {
            skateRepository.delete(Skate);
            return true;
        }).orElse(false);
        return d;
    }

}
