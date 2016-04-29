package cz.muni.fi.javaseminar.kafa.bookregister;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author martin.kalenda, Oldrich Faldik
 */
public class Author {

    private Long id;
    private String firstname;
    private String surname;
    private String description;

    public static class Builder {

        private Long id;
        private String firstname;
        private String surname;
        private String description;
        private String nationality;
        private LocalDate dateOfBirth;

        private Builder() {
        }

        public Builder id(final Long value) {
            this.id = value;
            return this;
        }

        public Builder firstname(final String value) {
            this.firstname = value;
            return this;
        }

        public Builder surname(final String value) {
            this.surname = value;
            return this;
        }

        public Builder description(final String value) {
            this.description = value;
            return this;
        }

        public Builder nationality(final String value) {
            this.nationality = value;
            return this;
        }

        public Builder dateOfBirth(final LocalDate value) {
            this.dateOfBirth = value;
            return this;
        }

        public Author build() {
            return new cz.muni.fi.javaseminar.kafa.bookregister.Author(id, firstname, surname, description, nationality, dateOfBirth);
        }
    }

    public static Author.Builder builder() {
        return new Author.Builder();
    }

    public Author(final Long id, final String firstname, final String surname, final String description, final String nationality, final LocalDate dateOfBirth) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.description = description;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
    }
    private String nationality;
    private LocalDate dateOfBirth;

    public String getFirstname() {
        return firstname;
    }

    public Author id(final Long value) {
        this.id = value;
        return this;
    }

    public Author firstname(final String value) {
        this.firstname = value;
        return this;
    }

    public Author surname(final String value) {
        this.surname = value;
        return this;
    }

    public Author description(final String value) {
        this.description = value;
        return this;
    }

    public Author nationality(final String value) {
        this.nationality = value;
        return this;
    }

    public Author dateOfBirth(final LocalDate value) {
        this.dateOfBirth = value;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.nationality);
        hash = 97 * hash + Objects.hashCode(this.dateOfBirth);
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
        final Author other = (Author) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }

}
