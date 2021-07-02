package URL;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepository extends CrudRepository<URL, Long> {
	URL getOne(long id);
	URL findByShortUrl(String shortUrl);
}
