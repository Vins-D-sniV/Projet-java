package com.projetSpring.api.controller;


import com.projetSpring.api.Dto.BookRequestDto;
import com.projetSpring.api.Dto.BookResponseDto;
import com.projetSpring.api.model.Book;
import com.projetSpring.api.repository.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public BookResponseDto createBook(@RequestBody BookRequestDto bookRequestDto) {

        return null;
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto) {return null;}

    @PatchMapping("/{id}")
    public BookResponseDto patchaBook(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto) {return null;}

    @DeleteMapping("/{id}")
    public BookResponseDto deleteBook(@PathVariable Long id) {return null;}
}
