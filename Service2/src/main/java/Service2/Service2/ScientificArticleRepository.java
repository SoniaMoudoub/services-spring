package Service2.Service2;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ScientificArticleRepository extends CrudRepository<ScientificArticle, Long>{
	List<ScientificArticle> findByname(String name);

}
