package Service1.Service1;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List<Customer> findByName(String name);
}
