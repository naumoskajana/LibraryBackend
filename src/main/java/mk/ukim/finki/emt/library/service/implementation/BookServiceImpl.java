package mk.ukim.finki.emt.library.service.implementation;

import mk.ukim.finki.emt.library.model.dto.BookDto;
import mk.ukim.finki.emt.library.model.entities.Author;
import mk.ukim.finki.emt.library.model.entities.Book;
import mk.ukim.finki.emt.library.model.enumeration.BookType;
import mk.ukim.finki.emt.library.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.library.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.library.model.exceptions.NoAvailableCopiesException;
import mk.ukim.finki.emt.library.repository.AuthorRepository;
import mk.ukim.finki.emt.library.repository.BookRepository;
import mk.ukim.finki.emt.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Book create(String name, BookType bookType, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name,bookType,author,availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String name, BookType bookType, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        book.setBookType(bookType);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
    }

    @Override
    public Book findByName(String name) {
        return this.bookRepository.findBookByName(name);
    }

    @Override
    public List<Book> findAllByBookType(BookType bookType) {
        return this.bookRepository.findAllByBookType(bookType);
    }

    @Override
    public List<Book> findAllByAuthor(Long authorId) {
        return this.bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public Integer bookTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        int availableCopies = book.getAvailableCopies();
        if (availableCopies == 0){
            throw new NoAvailableCopiesException(id);
        }
        else {
            availableCopies = availableCopies-1;
            book.setAvailableCopies(availableCopies);
            this.bookRepository.save(book);
        }
        return book.getAvailableCopies();
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        BookType bookType = BookType.valueOf(bookDto.getBookType());
        this.bookRepository.deleteByName(bookDto.getName());
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(),bookType,author,bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        BookType bookType = BookType.valueOf(bookDto.getBookType());
        book.setBookType(bookType);
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public List<BookType> listAllBookTypes() {
        return List.of(BookType.values());
    }
}
