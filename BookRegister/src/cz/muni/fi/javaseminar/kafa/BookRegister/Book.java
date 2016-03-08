package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.time.LocalDate;

/**
 *
 * @author martin.kalenda
 */
public class Book {
    private Long id;
    private String name;
    private String isbn;
    private LocalDate published;

    public Book(Long id, String name, String isbn, LocalDate published) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.published = published;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public Long getId() {
        return id;
    }
    
    
}
