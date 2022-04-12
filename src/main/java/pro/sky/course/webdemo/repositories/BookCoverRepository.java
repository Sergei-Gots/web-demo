package pro.sky.course.webdemo.repositories;

import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.course.webdemo.model.BookCover;

public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    Optional<BookCover> findByBookId(Long bookId);
}
