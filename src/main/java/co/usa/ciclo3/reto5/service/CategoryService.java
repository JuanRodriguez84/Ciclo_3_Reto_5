package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Category;
import co.usa.ciclo3.reto5.model.Category;
import co.usa.ciclo3.reto5.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriaRepository;

    public List<Category> getAll() {
        return categoriaRepository.getAll();
    }

    public Optional<Category> getCategoria(int id) {
        return categoriaRepository.getCategoria(id);
    }

    public Category save(Category p) {
        if (p.getId() == null) {
            return categoriaRepository.save(p);
        } else {
            Optional<Category> paux = categoriaRepository.getCategoria(p.getId());
            if (paux.isEmpty()) {
                return categoriaRepository.save(p);
            } else {
                return p;
            }
        }
    }

    public Category update(Category c) {
        if (c.getId()!= null) {
            Optional<Category> g = categoriaRepository.getCategoria(c.getId());
            if (!g.isEmpty()) {
                if (c.getName() != null) {
                    g.get().setName(c.getName());
                }
                if (c.getDescription() != null) {
                    g.get().setDescription(c.getDescription());
                }
                return categoriaRepository.save(g.get());
            }
        }
        return c;
    }

    public boolean deleteCategory(int id) {
        Boolean d = getCategoria(id).map(category -> {
            categoriaRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
