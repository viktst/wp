package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookRepository bookRepository;

    public BookReservationServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, Long numberOfCopies) {
        Book book = bookRepository.findAll()
                .stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(bookTitle))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book with title '" + bookTitle + "' not found!"));

        BookReservation reservation = new BookReservation(
                (long) (DataHolder.reservations.size() + 1),
                bookTitle,
                readerName,
                readerAddress,
                numberOfCopies,
                book
        );

        DataHolder.reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<BookReservation> listAll() {
        return DataHolder.reservations;
    }
}
