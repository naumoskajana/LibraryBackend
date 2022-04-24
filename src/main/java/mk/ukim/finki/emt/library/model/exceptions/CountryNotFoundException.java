package mk.ukim.finki.emt.library.model.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super(String.format("Country with %d not found", id));
    }
}
