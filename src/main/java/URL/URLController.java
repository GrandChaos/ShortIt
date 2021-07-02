package URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
public class URLController {

    @Autowired
    URLRepository repository;
    private static SecureRandom random = new SecureRandom();
    private String small;

    public static String shortUrlGenerate()
    {
        return new BigInteger(24, random).toString(32);
    }

    @GetMapping("/ShortIt")
    public String ShortIt(Model model) {
        model.addAttribute("URL", new URL("",""));
        model.addAttribute("done", false);
        model.addAttribute("stats", false);
        return "ShortIt";
    }

    @PostMapping("/ShortIt")
    public String ShortIt(@ModelAttribute URL url, Model model) {
        model.addAttribute("URL", url);
        model.addAttribute("done", false);
        model.addAttribute("stats", false);
        String shortUrl = shortUrlGenerate();
        small = shortUrl;
        repository.save(new URL(url.getFullUrl(), shortUrl));
        return "redirect:/ShortItDone";
    }

    @GetMapping("/ShortItDone")
    public String shortURlDone(Model model) {
        model.addAttribute("done", true);
        model.addAttribute("stats", false);
        URL Url = repository.findByShortUrl(small);
        model.addAttribute("URL", Url);
        return "ShortIt";
    }

    @GetMapping(path = "/{reduced}")
    public ResponseEntity redirect(@PathVariable("reduced") String shortUrl) {
        //TODO find hash in DB and redirect to original URL
        URL Url = repository.findByShortUrl(shortUrl);
        if (Url != null) {
            Url.incCountClick();
            repository.save(Url);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", Url.getFullUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("ShortIt/stats")
    public String stats(@RequestParam(value = "id", required = true) String id, Model model){
        URL url = repository.getOne(Long.valueOf(id).longValue());
        if (url != null){
            model.addAttribute("URL", url);
            model.addAttribute("done", true);
            model.addAttribute("stats", true);
            return "ShortIt";
        }
        else {
            return "redirect:/ShortIt";
        }
    }
}
