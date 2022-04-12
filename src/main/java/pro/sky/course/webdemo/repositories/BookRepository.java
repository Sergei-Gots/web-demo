package pro.sky.course.webdemo.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.course.webdemo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByNameIgnoreCase(String name);

    Collection<Book> findBooksByAuthorContainsIgnoreCase(String author);

    Collection<Book> findAllByNameContainsIgnoreCase(String part);

    Collection<Book> findBooksByNameIgnoreCaseAndAuthor(String name, String author);

    Collection<Book> findBooksByNameOrAuthorAndIdGreaterThan(String name, String author, Long number);

}
