package mk.ukim.finki.emt.library.model.dto;

public class BookDto {
    private String name;
    private String bookType;
    private Long author;
    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, String bookType, Long author, Integer availableCopies) {
        this.name = name;
        this.bookType = bookType;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
