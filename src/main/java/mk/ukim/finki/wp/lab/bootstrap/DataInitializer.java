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

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookReservationRepository reservationRepository;

    @PostConstruct
    public void init() {
        if (authorRepository.count() == 0)
            initAuthors();
        if (bookRepository.count() == 0)
            initBooks();
        if (reservationRepository.count() == 0)
            initReservations();
    }

    private void initAuthors() {
        Author author1 = new Author();
        author1.setName("Robert");
        author1.setSurname("Martin");
        author1.setCountry("USA");
        author1.setBiography("Software engineer");

        Author author2 = new Author();
        author2.setName("Steve");
        author2.setSurname("Stevenson");
        author2.setCountry("UK");
        author2.setBiography("Fiction writer");

        Author author3 = new Author();
        author3.setName("George");
        author3.setSurname("Georges");
        author3.setCountry("Macedonia");
        author3.setBiography("Novelist and essayist");

        authorRepository.saveAll(List.of(author1, author2, author3));
    }

    private void initBooks() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) return;

        Book book1 = new Book();
        book1.setTitle("Domain Driven Design");
        book1.setGenre("Software Architecture");
        book1.setAverageRating(4.7);
        book1.setAvailableCopies(5);
        book1.setAuthor(authors.get(0));

        Book book2 = new Book();
        book2.setTitle("Harry Potter");
        book2.setGenre("Fantasy");
        book2.setAverageRating(2.9);
        book2.setAvailableCopies(5);
        book2.setAuthor(authors.get(1));

        Book book3 = new Book();
        book3.setTitle("The Hobbit");
        book3.setGenre("Fantasy");
        book3.setAverageRating(4.7);
        book3.setAvailableCopies(5);
        book3.setAuthor(authors.get(2));

        bookRepository.saveAll(List.of(book1, book2, book3));
    }

    private void initReservations() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) return;

        BookReservation r1 = new BookReservation();
        r1.setBookTitle("Domain Driven Design");
        r1.setReaderName("John Johnson");
        r1.setReaderAddress("Los Angeles, USA");
        r1.setNumberOfCopies(2);
        r1.setReservationDate(LocalDateTime.now());

        BookReservation r2 = new BookReservation();
        r2.setBookTitle("The Hobbit");
        r2.setReaderName("Bob Smith");
        r2.setReaderAddress("London, UK");
        r2.setNumberOfCopies(1);
        r2.setReservationDate(LocalDateTime.now());

        reservationRepository.saveAll(List.of(r1, r2));

        updateBookCopies(books, List.of(r1, r2));
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
