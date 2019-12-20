
package com.sinfloo.ejemplo01;


import java.util.List;
import org.springframework.data.repository.Repository;

public interface PersonaRepositorio extends Repository<Persona, Integer>{
    List<Persona>findAll();
    Persona findOne(int id);
    Persona save(Persona p);
    void delete(Persona p);
}
