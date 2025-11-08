package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text, rating);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findAll().stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = new Book(null, title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, Double averageRating, Long authorId) {
        Author author = authorRepository.findAll().stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = new Book(id, title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}