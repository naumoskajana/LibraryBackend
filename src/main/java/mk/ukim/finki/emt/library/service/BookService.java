package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.entities.Book;
import mk.ukim.finki.emt.library.model.enumeration.BookType;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> findById(Long id);
    Book create(String name, BookType bookType, Long authorId, Integer availableCopies);
    Book edit(Long id, String name, BookType bookType, Long authorId, Integer availableCopies);
    void delete(Long id);
    Book findByName(String name);
    List<Book> findAllByBookType(BookType bookType);
    List<Book> findAllByAuthor(Long authorId);
    Integer bookTaken(Long id);

    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);

    List<BookType> listAllBookTypes();
}
