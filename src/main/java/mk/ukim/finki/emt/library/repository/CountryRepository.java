package mk.ukim.finki.emt.library.repository;

import mk.ukim.finki.emt.library.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
