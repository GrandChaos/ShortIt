package URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class URL {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String fullUrl;
	private String shortUrl;
	private int countClicks;

	protected URL() {}

	public URL(String defaultURL, String shortURL) {
		this.fullUrl = defaultURL;
		this.shortUrl = shortURL;
		countClicks = 0;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFullUrl(){
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortUrl(){
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public int getCountClicks() {
		return countClicks;
	}
	public void setCountClicks(int countClicks) {
		this.countClicks = countClicks;
	}
	public void incCountClick(){
		countClicks++;
	}
}
