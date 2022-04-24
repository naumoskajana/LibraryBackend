package mk.ukim.finki.emt.library.service.implementation;

import mk.ukim.finki.emt.library.model.dto.AuthorDto;
import mk.ukim.finki.emt.library.model.entities.Author;
import mk.ukim.finki.emt.library.model.entities.Book;
import mk.ukim.finki.emt.library.model.entities.Country;
import mk.ukim.finki.emt.library.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.library.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import mk.ukim.finki.emt.library.repository.CountryRepository;
import mk.ukim.finki.emt.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name,surname,country);
        return this.authorRepository.save(author);
    }

}
