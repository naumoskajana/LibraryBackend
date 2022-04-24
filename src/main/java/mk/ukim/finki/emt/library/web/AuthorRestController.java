package mk.ukim.finki.emt.library.web;

import mk.ukim.finki.emt.library.model.dto.AuthorDto;
import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.entities.Author;
import mk.ukim.finki.emt.library.model.entities.Country;
import mk.ukim.finki.emt.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll(){
        return this.authorService.listAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) {
        return this.authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
