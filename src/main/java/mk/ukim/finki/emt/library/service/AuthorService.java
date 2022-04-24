package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.dto.AuthorDto;
import mk.ukim.finki.emt.library.model.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAll();
    Author findById(Long id);
    Author findByName(String name);
    Author create(String name, String surname, Long countryId);
    Author edit(Long id, String name, String surname, Long countryId);
    void deleteById(Long id);
    List<Author> findByCountry(Long countryId);
    Optional<Author> save(AuthorDto authorDto);
}
