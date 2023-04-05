package monarchapp.com.proyecto1.repositories;

import monarchapp.com.proyecto1.entities.GrasaSolidoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrasaSolidoRepository extends CrudRepository<GrasaSolidoEntity,Long> {
}
