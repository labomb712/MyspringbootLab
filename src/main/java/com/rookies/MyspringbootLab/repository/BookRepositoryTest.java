package com.rookies.MyspringbootLab.repository;

import com.rookies.MyspringbootLab.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class BookRepositoryTest {
    private com.packt.myspringbootlab2.repository.BookRepository bookRepository;

    // 도서 등록 테스트
    @Test
    public void testCreateBook() {
        Book book = new Book(null, "Clean Code", "Robert C. Martin", "9780132350884", LocalDate.of(2008, 8, 1), 40000);
        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Clean Code");
    }

    // ISBN으로 도서 조회 테스트
    @Test
    public void testFindByIsbn() {
        Book book = new Book(null, "Effective Java", "Joshua Bloch", "9780134685991", LocalDate.of(2018, 1, 6), 45000);
        bookRepository.save(book);

        Book found = bookRepository.findByIsbn("9780134685991");
        assertThat(found).isNotNull();
        assertThat(found.getAuthor()).isEqualTo("Joshua Bloch");
    }

    // 🧑‍저자명으로 도서 목록 조회 테스트
    @Test
    public void testFindByAuthor() {
        Book book1 = new Book(null, "Java Concurrency in Practice", "Brian Goetz", "9780321349606", LocalDate.of(2006, 5, 9), 42000);
        Book book2 = new Book(null, "Java Puzzlers", "Brian Goetz", "9780321336781", LocalDate.of(2005, 7, 1), 38000);
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findByAuthor("Brian Goetz");
        assertThat(books).hasSize(2);
    }

    // 도서 정보 수정 테스트
    @Test
    public void testUpdateBook() {
        Book book = new Book(null, "Refactoring", "Martin Fowler", "9780201485677", LocalDate.of(1999, 6, 28), 50000);
        Book saved = bookRepository.save(book);

        saved.setPrice(55000);
        Book updated = bookRepository.save(saved);

        assertThat(updated.getPrice()).isEqualTo(55000);
    }

    // 도서 정보 삭제 테스트
    @Test
    public void testDeleteBook() {
        Book book = new Book(null, "Test-Driven Development", "Kent Beck", "9780321146533", LocalDate.of(2002, 11, 8), 37000);
        Book saved = bookRepository.save(book);

        bookRepository.delete(saved);
        Optional<Book> deleted = bookRepository.findById(saved.getId());

        assertThat(deleted).isEmpty();
    }
}