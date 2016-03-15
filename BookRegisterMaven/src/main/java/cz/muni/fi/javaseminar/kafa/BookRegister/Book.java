package cz.muni.fi.javaseminar.kafa.BookRegister;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author martin.kalenda, Oldrich Faldik
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.isbn);
        hash = 53 * hash + Objects.hashCode(this.published);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.published, other.published)) {
            return false;
        }
        return true;
    }
    
    
    
}