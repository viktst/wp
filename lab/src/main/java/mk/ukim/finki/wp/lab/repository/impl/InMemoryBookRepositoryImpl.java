package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepositoryImpl implements BookRepository {

    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        Predicate<Book> titleFilter = book ->
                text == null || text.isEmpty() ||
                        book.getTitle().toLowerCase().contains(text.toLowerCase());

        Predicate<Book> ratingFilter = book ->
                rating == null || book.getAverageRating() >= rating;

        return DataHolder.books.stream()
                .filter(titleFilter.and(ratingFilter))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            Long maxId = DataHolder.books.stream()
                    .mapToLong(Book::getId)
                    .max()
                    .orElse(0L);
            book.setId(maxId + 1);
        }

        DataHolder.books.removeIf(b -> b.getId().equals(book.getId()));
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }
}