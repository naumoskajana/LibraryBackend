package mk.ukim.finki.emt.library.repository;

import mk.ukim.finki.emt.library.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
