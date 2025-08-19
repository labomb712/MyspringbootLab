package com.rookies.MyspringbootLab.service;

import com.rookies.MyspringbootLab.dto.BookDTO;
import com.rookies.MyspringbootLab.entity.Book;
import com.rookies.MyspringbootLab.Exception.BusinessException;
import com.rookies.MyspringbootLab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {
        Book book = bookRepository.save(request.toEntity());
        return BookDTO.BookResponse.fromEntity(book);
    }

    @Transactional(readOnly = true)
    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO.BookResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookDTO.BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        return BookDTO.BookResponse.fromEntity(book);
    }

    @Transactional(readOnly = true)
    public BookDTO.BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        return BookDTO.BookResponse.fromEntity(book);
    }

    public BookDTO.BookResponse updateBook(Long id, BookDTO.BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getPublishDate() != null) book.setPublishDate(request.getPublishDate());
        if (request.getPrice() != null) book.setPrice(request.getPrice());

        Book updatedBook = bookRepository.save(book);
        return BookDTO.BookResponse.fromEntity(updatedBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }

    @Transactional(readOnly = true)
    public List<BookDTO.BookResponse> findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(BookDTO.BookResponse::fromEntity)
                .collect(Collectors.toList());
    }
}