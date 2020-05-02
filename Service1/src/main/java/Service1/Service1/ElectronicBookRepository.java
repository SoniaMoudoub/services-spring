package Service1.Service1;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface ElectronicBookRepository extends CrudRepository<ElectronicBook, Long> {
	List<ElectronicBook> findBycote(String cote);
}
