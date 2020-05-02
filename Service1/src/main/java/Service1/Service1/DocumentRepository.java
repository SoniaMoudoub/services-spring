package Service1.Service1;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {
	
	List<Document> findBycote(String cote);

}