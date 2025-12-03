package com.projetSpring.api.service;


import com.projetSpring.api.Dto.BookRequestDto;
import com.projetSpring.api.Dto.BookResponseDto;
import com.projetSpring.api.Exception.BookNotFoundException;
import com.projetSpring.api.model.Book;
import com.projetSpring.api.repository.BookRepository;
import com.projetSpring.api.repository.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl  implements BookService {
    private final BookRepository bookRepository;


    private static final Set<String> PATCHABLE_FIELDS = Set.of("title", "author", "publishedDate");

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livre avec id " + id + " introuvable"));
        return toDto(book);
    }

    @Override
    public BookResponseDto createBook(BookRequestDto request) {
        Book book = fromRequest(request);
        Book saved = bookRepository.save(book);
        return toDto(saved);
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livre avec id " + id + " introuvable"));

        // Remplacement complet (PUT)
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublishedDate(request.getPublishedDate());

        Book saved = bookRepository.save(book);
        return toDto(saved);
    }

    @Override
    public BookResponseDto patchBook(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livre avec id " + id + " introuvable"));

        // Appliquer seulement les champs autorisés et faire une conversion simple
        updates.forEach((key, value) -> {
            if (!PATCHABLE_FIELDS.contains(key)) {
                throw new IllegalArgumentException("Champ non modifiable : " + key);
            }

            Field field = ReflectionUtils.findField(Book.class, key);
            if (field == null) {
                throw new IllegalArgumentException("Champ inconnu : " + key);
            }
            field.setAccessible(true);

            Object converted = convertValueIfNeeded(field, value);
            try {
                field.set(book, converted);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Impossible de mettre à jour le champ : " + key, e);
            }
        });

        Book saved = bookRepository.save(book);
        return toDto(saved);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Livre avec id " + id + " introuvable");
        }
        bookRepository.deleteById(id);
    }

    // -------------------------------
    // Conversion Entity <-> DTO
    // -------------------------------

    private BookResponseDto toDto(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublishedDate(book.getPublishedDate());
        return dto;
    }

    private Book fromRequest(BookRequestDto request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublishedDate(request.getPublishedDate());
        return book;
    }

    // Conversion basique pour le patch (prise en charge LocalDate si besoin)
    private Object convertValueIfNeeded(Field field, Object value) {
        if (value == null) return null;
        Class<?> targetType = field.getType();

        if (targetType.equals(LocalDate.class)) {
            if (value instanceof String) {
                return LocalDate.parse((String) value); // attendre ISO-8601 yyyy-MM-dd
            } else if (value instanceof LocalDate) {
                return value;
            } else {
                throw new IllegalArgumentException("Format de date invalide pour " + field.getName());
            }
        }

        // conversions simples pour String, Long, Integer, etc.
        if (targetType.equals(String.class)) {
            return value.toString();
        }
        if (targetType.equals(Long.class) || targetType.equals(long.class)) {
            if (value instanceof Number) return ((Number) value).longValue();
            return Long.parseLong(value.toString());
        }
        if (targetType.equals(Integer.class) || targetType.equals(int.class)) {
            if (value instanceof Number) return ((Number) value).intValue();
            return Integer.parseInt(value.toString());
        }

        // fallback (essayer d'assigner tel quel)
        return value;
    }
}
