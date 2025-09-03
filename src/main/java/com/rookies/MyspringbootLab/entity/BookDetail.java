package com.rookies.MyspringbootLab.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity
@Table(name = "book_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class BookDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String language;

    @Column(name = "page_count")
    private Integer pageCount;

    private String publisher;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    private String edition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", unique = true)
    private Book book;
}