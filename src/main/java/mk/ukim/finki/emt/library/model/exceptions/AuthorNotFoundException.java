package mk.ukim.finki.emt.library.model.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with %d not found", id));
    }
}
