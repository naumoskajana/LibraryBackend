package mk.ukim.finki.emt.library.service.implementation;

import mk.ukim.finki.emt.library.model.dto.CountryDto;
import mk.ukim.finki.emt.library.model.entities.Country;
import mk.ukim.finki.emt.library.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.library.repository.CountryRepository;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name,continent);
        return this.countryRepository.save(country);
    }

}
