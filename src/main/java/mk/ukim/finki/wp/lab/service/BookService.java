package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    List<Book> searchBooks(String title);
    Optional<Book> findById(Long id);
    Book create(String title, String genre, Double averageRating, Long authorId);
    Book update(Long id, String title, String genre, Double averageRating, Long authorId);
    void deleteById(Long id);
    List<Book> getAvailableBooks();
    boolean reserveBookCopies(Long bookId, Integer numberOfCopies);
}