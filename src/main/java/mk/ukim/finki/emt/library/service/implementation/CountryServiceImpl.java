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
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name,continent);
        return this.countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(name);
        country.setContinent(continent);
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
        Country country = this.countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
        this.countryRepository.delete(country);
    }

    @Override
    public Country findByName(String name) {
        return this.countryRepository.findCountryByName(name);
    }

    @Override
    public List<Country> findAllByContinent(String continent) {
        return this.countryRepository.findAllByContinent(continent);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        return Optional.of(this.countryRepository.save(new Country(countryDto.getName(),countryDto.getContinent())));
    }
}
