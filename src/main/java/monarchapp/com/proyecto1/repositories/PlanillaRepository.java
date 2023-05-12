package monarchapp.com.proyecto1.repositories;

import monarchapp.com.proyecto1.entities.PlanillaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends CrudRepository<PlanillaEntity,Long> {
}
