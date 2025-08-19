package com.rookies.MyspringbootLab.dto;

import com.rookies.MyspringbootLab.entity.Book;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

public class BookDTO {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookCreateRequest {
        @NotBlank(message = "제목은 필수 입력 항목입니다.")
        private String title;

        @NotBlank(message = "저자는 필수 입력 항목입니다.")
        private String author;

        @NotBlank(message = "ISBN은 필수 입력 항목입니다.")
        private String isbn;

        @NotNull(message = "출판일은 필수 입력 항목입니다.")
        @PastOrPresent(message = "출판일은 현재 또는 과거 날짜여야 합니다.")
        private LocalDate publishDate;

        @NotNull(message = "가격은 필수 입력 항목입니다.")
        @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
        private Integer price;

        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .author(author)
                    .isbn(isbn)
                    .publishDate(publishDate)
                    .price(price)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookUpdateRequest {
        private String title;
        private String author;
        private LocalDate publishDate;
        private Integer price;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private LocalDate publishDate;
        private Integer price;

        public static BookResponse fromEntity(Book book) {
            return BookResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .publishDate(book.getPublishDate())
                    .price(book.getPrice())
                    .build();
        }
    }
}