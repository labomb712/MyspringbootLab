package com.rookies.MyspringbootLab.repository;

import com.rookies.MyspringbootLab.repository.BookRepository;
import com.rookies.MyspringbootLab.entity.Book;
import org.hibernate.annotations.DynamicUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Transactional
@DynamicUpdate
//@RequiredArgsConstructor
public class BookRepositoryTest {

//    private final BookRepository bookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
//    @Rollback(value = false)
    public void testCreateBook(){

        Book book = new Book();
        book.setTitle("스프링 부트 입문");
        book.setAuthor("홍길동");
        book.setIsbn("9788956746425");
        book.setPrice(30000);
        book.setPublishDate(LocalDate.parse("2025-05-07"));

        Book savedBook = bookRepository.save(book);
        System.out.println("savedBook = " + savedBook);
    }

    @Test
    public void testFindByIsbn() {

        Optional<Book> bookByIsbn = bookRepository.findByIsbn("9788956746425");
        if(bookByIsbn.isPresent()){
            Book existingBook = bookByIsbn.get();
            assertThat(existingBook.getIsbn()).isEqualTo("9788956746425");
        }
    }

    @Test
    public void testFindByAuthor() {
        List<Book> booksByAuthor = bookRepository.findByAuthor("홍길동");
        if(booksByAuthor.isEmpty()){
            System.out.println("No books found for the author.");
        }
        assertThat(booksByAuthor).isNotEmpty();
        for (Book book : booksByAuthor) {
            System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
        }

    }

    @Test
    public void testUpdateBook() {
        Optional<Book> bookToEdit = bookRepository.findByIsbn("9788956746425");
        Book editedBook = new Book();

        if (bookToEdit.isPresent()) {
            Book book = bookToEdit.get();
            book.setTitle("JPA 프로그래밍");
            book.setPrice(35000);
            book.setPublishDate(LocalDate.parse("2025-04-30"));
            book.setIsbn("9788956746432");
            book.setAuthor("박둘리");

            editedBook = bookRepository.save(book);
        }
        assertThat(editedBook.getTitle()).isEqualTo("JPA 프로그래밍");
        System.out.println("수정된 책 : " + editedBook.getTitle());
    }

    @Test
    public void testDeleteBook(){
        Optional<Book> bookToDelete = bookRepository.findByIsbn("9788956746432");
        if (bookToDelete.isPresent()) {
            Book book = bookToDelete.get();
            bookRepository.delete(book);
            System.out.println("책이 삭제되었습니다: " + book.getTitle());
        } else {
            System.out.println("책을 찾을 수 없습니다.");
        }

    }

}