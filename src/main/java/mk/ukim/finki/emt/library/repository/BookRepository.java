package mk.ukim.finki.emt.library.repository;

import mk.ukim.finki.emt.library.model.entities.Book;
import mk.ukim.finki.emt.library.model.enumeration.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findBookByName(String name);
    List<Book> findAllByBookType(BookType bookType);
    List<Book> findAllByAuthor_Id(Long id);
    void deleteByName(String name);
}
