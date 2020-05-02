package Service2.Service2;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface PersonRepository  extends CrudRepository<Person, Long>{
	
	List<Person> findByName(String name);
}
