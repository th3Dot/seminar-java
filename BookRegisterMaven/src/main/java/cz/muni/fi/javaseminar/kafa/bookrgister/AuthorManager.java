package cz.muni.fi.javaseminar.kafa.bookrgister;

import java.util.List;

/**
 *
 * @author martin.kalenda
 */
public interface AuthorManager {
    public void createAuthor(Author author);
    public void updateAuthor(Author author);
    public void deleteAuthor(Author author);
    public List<Author> findAllAuthors();
    public Author findAuthorById(Long id);
}
