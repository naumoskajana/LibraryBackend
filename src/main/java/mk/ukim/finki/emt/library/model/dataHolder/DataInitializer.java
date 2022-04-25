package mk.ukim.finki.emt.library.model.dataHolder;

import mk.ukim.finki.emt.library.model.entities.Author;
import mk.ukim.finki.emt.library.model.entities.Country;
import mk.ukim.finki.emt.library.model.enumeration.BookType;
import mk.ukim.finki.emt.library.service.AuthorService;
import mk.ukim.finki.emt.library.service.BookService;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private final CountryService countryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public DataInitializer(CountryService countryService, AuthorService authorService, BookService bookService) {
        this.countryService = countryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostConstruct
    public void initData(){
        this.countryService.create("Country1","Continent1");
        this.countryService.create("Country2","Continent2");
        this.countryService.create("Country3","Continent3");
        this.countryService.create("Country4","Continent4");
        this.countryService.create("Country5","Continent5");

        List<Country> countries = this.countryService.listAll();
        this.authorService.create("AuthorName1","AuthorSurname1",countries.get(0).getId());
        this.authorService.create("AuthorName2","AuthorSurname2",countries.get(1).getId());
        this.authorService.create("AuthorName3","AuthorSurname3",countries.get(2).getId());
        this.authorService.create("AuthorName4","AuthorSurname4",countries.get(3).getId());
        this.authorService.create("AuthorName5","AuthorSurname5",countries.get(4).getId());

        List<Author> authors = this.authorService.listAll();
        Random random = new Random();
        int min = 1;
        int max = 100;
        this.bookService.create("Book1", BookType.BIOGRAPHY,authors.get(0).getId(), random.nextInt((max-min)+1)+min);
        this.bookService.create("Book2", BookType.CLASSICS,authors.get(1).getId(), random.nextInt((max-min)+1)+min);
        this.bookService.create("Book3", BookType.DRAMA,authors.get(2).getId(), random.nextInt((max-min)+1)+min);
        this.bookService.create("Book4", BookType.FANTASY,authors.get(3).getId(), random.nextInt((max-min)+1)+min);
        this.bookService.create("Book5", BookType.HISTORY,authors.get(4).getId(), random.nextInt((max-min)+1)+min);
    }

}
