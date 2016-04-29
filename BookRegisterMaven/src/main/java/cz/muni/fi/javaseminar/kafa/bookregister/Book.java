package cz.muni.fi.javaseminar.kafa.bookregister;

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
    private Long authorId;

    public String getName() {
        return name;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String isbn;
        private LocalDate published;
        private Long authorId;

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

        public Builder id(final Long value) {
            this.id = value;
            return this;
        }

        public Builder name(final String value) {
            this.name = value;
            return this;
        }

        public Builder isbn(final String value) {
            this.isbn = value;
            return this;
        }

        public Builder published(final LocalDate value) {
            this.published = value;
            return this;
        }

        public Builder authorId(final Long value) {
            this.authorId = value;
            return this;
        }

        private Builder() {
        }

        public Book build() {
            return new cz.muni.fi.javaseminar.kafa.bookregister.Book(id, name, isbn, published, authorId);
        }
    }

    public static Book.Builder builder() {
        return new Book.Builder();
    }

    public Book(final Long id, final String name, final String isbn, final LocalDate published, final Long authorId) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.published = published;
        this.authorId = authorId;
    }

    public Book id(final Long value) {
        this.id = value;
        return this;
    }

    public Book name(final String value) {
        this.name = value;
        return this;
    }

    public Book authorId(final Long value) {
        this.authorId = value;
        return this;
    }

    public Book isbn(final String value) {
        this.isbn = value;
        return this;
    }

    public Book published(final LocalDate value) {
        this.published = value;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public void setId(long id) {
        this.id = id;
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
