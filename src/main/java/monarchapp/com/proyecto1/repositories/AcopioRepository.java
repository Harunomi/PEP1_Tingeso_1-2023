package monarchapp.com.proyecto1.repositories;

import monarchapp.com.proyecto1.entities.AcopioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcopioRepository  extends CrudRepository<AcopioEntity,Long> {
}
