package URL;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepository extends CrudRepository<URL, Long> {
	URL findById(long id);
	URL findByShortUrl(String shortUrl);
}
