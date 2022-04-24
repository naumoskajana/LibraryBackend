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
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author findByName(String name) {
        return this.authorRepository.findAuthorByName(name);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name,surname,country);
        return this.authorRepository.save(author);
    }

    @Override
    public Author edit(Long id, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return this.authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        this.authorRepository.delete(author);
    }

    @Override
    public List<Author> findByCountry(Long countryId) {
        return this.authorRepository.findAllByCountry_Id(countryId);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        this.authorRepository.deleteByName(authorDto.getName());
        return Optional.of(this.authorRepository.save(new Author(authorDto.getName(),authorDto.getSurname(),country)));
    }
}
