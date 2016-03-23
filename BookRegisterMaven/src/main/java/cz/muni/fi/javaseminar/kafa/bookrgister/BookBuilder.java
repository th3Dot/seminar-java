/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.javaseminar.kafa.bookrgister;

import java.time.LocalDate;

public class BookBuilder {

    private Long id;
    private String name;
    private String isbn = null;
    private LocalDate published = null;

    public BookBuilder() {
    }

    public BookBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public BookBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setPublished(LocalDate published) {
        this.published = published;
        return this;
    }

}
