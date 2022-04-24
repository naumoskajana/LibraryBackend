package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.dto.AuthorDto;
import mk.ukim.finki.emt.library.model.dto.CountryDto;
import mk.ukim.finki.emt.library.model.entities.Author;
import mk.ukim.finki.emt.library.model.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Country create(String name, String continent);
}
