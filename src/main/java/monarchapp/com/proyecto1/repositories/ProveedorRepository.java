package monarchapp.com.proyecto1.repositories;


import monarchapp.com.proyecto1.entities.ProveedorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProveedorRepository extends CrudRepository<ProveedorEntity,Long>{
    public void deleteAll();
    public ArrayList<ProveedorEntity> findAll();
}

