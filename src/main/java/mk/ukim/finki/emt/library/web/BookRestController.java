package mk.ukim.finki.emt.library.web;

import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.entities.Book;
import mk.ukim.finki.emt.library.model.enumeration.BookType;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://emtlab2frontend193018.herokuapp.com")
@RequestMapping("/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.listAll();
    }

    @GetMapping("/bookTypes")
    public List<BookType> findAllBookTypes(){
        return this.bookService.listAllBookTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.delete(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/markAsTaken/{id}")
    public ResponseEntity markAsTaken(@PathVariable Long id) {
        int availableCopiesBefore = this.bookService.findById(id).get().getAvailableCopies();
        int availableCopiesAfter = this.bookService.bookTaken(id);
        if (availableCopiesAfter == availableCopiesBefore-1){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
