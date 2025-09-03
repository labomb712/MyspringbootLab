package com.rookies.MyspringbootLab.controller;

import com.rookies.MyspringbootLab.DTO.BookDTO;
import com.rookies.MyspringbootLab.DTO.BookDTO;
import com.rookies.MyspringbootLab.DTO.PublisherDTO;
import com.rookies.MyspringbootLab.DTO.PublisherDTO;
import com.rookies.MyspringbootLab.service.BookService;
import com.rookies.MyspringbootLab.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<PublisherDTO.SimpleResponse>> getAllPublishers() {
        List<PublisherDTO.SimpleResponse> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO.Response> getPublisherById(@PathVariable Long id) {
        PublisherDTO.Response publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PublisherDTO.Response> getPublisherByName(@PathVariable String name) {
        PublisherDTO.Response publisher = publisherService.getPublisherByName(name);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO.Response>> getBooksByPublisherId(@PathVariable Long id) {
        List<BookDTO.Response> books = bookService.getBooksByPublisherId(id);
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<PublisherDTO.Response> createPublisher(@Valid @RequestBody PublisherDTO.Request request) {
        PublisherDTO.Response createdPublisher = publisherService.createPublisher(request);
        return new ResponseEntity<>(createdPublisher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO.Response> updatePublisher(
            @PathVariable Long id,
            @Valid @RequestBody PublisherDTO.Request request) {
        PublisherDTO.Response updatedPublisher = publisherService.updatePublisher(id, request);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}