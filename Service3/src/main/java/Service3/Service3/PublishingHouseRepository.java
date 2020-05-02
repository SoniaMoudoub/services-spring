package Service3.Service3;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouse, Long>{
	List<PublishingHouse> findByName(String name);
}
