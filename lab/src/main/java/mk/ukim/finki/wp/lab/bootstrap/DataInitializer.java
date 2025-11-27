package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookReservationRepository reservationRepository;

    @PostConstruct
    public void init() {
        if (authorRepository.count() == 0) {
            initData();
        }
    }

    private void initData() {
        // avtori
        List<Author> authors = List.of(
                new Author("Robert", "Martin", "USA", "Foftware engineer"),
                new Author("Steve", "Stevenson", "UK", "Fiction writer"),
                new Author("George", "Georges", "Macedonia", "Novelist and essayist")
        );
        authorRepository.saveAll(authors);

        // knigi
        List<Book> books = List.of(
                new Book("Domain Driven Design", "Software Architecture", 4.7, authors.get(0)),
                new Book("Harry Potter", "Fantasy", 2.9, authors.get(1)),
                new Book("The Hobbit", "Fantasy", 4.7, authors.get(2))
        );
        bookRepository.saveAll(books);

        // rezervacii
        List<BookReservation> reservations = List.of(
                new BookReservation("Domain Driven Design", "John Johnson", "Los Angeles, USA", 2),
                new BookReservation("The Hobbit", "Bob Smith", "London, UK", 1)
        );
        reservationRepository.saveAll(reservations);

        updateBookCopies(books, reservations);
    }

    private void updateBookCopies(List<Book> books, List<BookReservation> reservations) {
        for (BookReservation reservation : reservations) {
            for (Book book : books) {
                if (book.getTitle().equals(reservation.getBookTitle())) {
                    int newCopies = book.getAvailableCopies() - reservation.getNumberOfCopies();
                    book.setAvailableCopies(newCopies);
                    bookRepository.save(book);
                    break;
                }
            }
        }
    }
}