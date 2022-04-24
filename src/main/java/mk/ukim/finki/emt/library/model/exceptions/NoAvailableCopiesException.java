package mk.ukim.finki.emt.library.model.exceptions;

public class NoAvailableCopiesException extends RuntimeException{
    public NoAvailableCopiesException(Long id) {
        super(String.format("Book with %d does not have available copies", id));
    }
}
