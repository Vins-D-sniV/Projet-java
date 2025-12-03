package com.projetSpring.api.repository;



import com.projetSpring.api.Dto.BookRequestDto;
import com.projetSpring.api.Dto.BookResponseDto;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<BookResponseDto> getAllBooks();

    BookResponseDto getBookById(Long id);

    BookResponseDto createBook(BookRequestDto request);

    BookResponseDto updateBook(Long id, BookRequestDto request);

    BookResponseDto patchBook(Long id, Map<String, Object> updates);

    void deleteBook(Long id);
}
