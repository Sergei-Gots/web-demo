package pro.sky.course.webdemo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import javax.xml.transform.sax.SAXResult;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.course.webdemo.controllers.BooksController;
import pro.sky.course.webdemo.model.Book;
import pro.sky.course.webdemo.repositories.BookCoverRepository;
import pro.sky.course.webdemo.repositories.BookRepository;
import pro.sky.course.webdemo.services.BookCoverService;
import pro.sky.course.webdemo.services.BookService;

@WebMvcTest
public class WebDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookCoverRepository bookCoverRepository;

    @SpyBean
    private BookService bookService;

    @SpyBean
    private BookCoverService bookCoverService;

    @InjectMocks
    private BooksController booksController;

    @Test
    public void saveBookTest() throws Exception {
        final String name = "1231232";
        final String author = "Sdfasf";
        final long id = 1;

        JSONObject bookObject = new JSONObject();
        bookObject.put("name", name);
        bookObject.put("author", author);

        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);

        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findById(eq(1L))).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/books")
                        .content(bookObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
    }

}
