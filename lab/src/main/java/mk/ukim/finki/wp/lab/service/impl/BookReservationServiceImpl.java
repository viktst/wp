package mk.ukim.finki.wp.lab.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookReservation createReservation(Long bookId, String readerName,
                                             String readerAddress, Integer numberOfCopies) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        if (book.getAvailableCopies() < numberOfCopies) {
            throw new IllegalArgumentException(
                    String.format("Not enough copies available. Requested: %d, Available: %d",
                            numberOfCopies, book.getAvailableCopies())
            );
        }

        book.setAvailableCopies(book.getAvailableCopies() - numberOfCopies);
        bookRepository.save(book);

        BookReservation reservation = new BookReservation(
                book.getTitle(), readerName, readerAddress, numberOfCopies
        );

        return reservationRepository.save(reservation);
    }

    @Override
    public List<BookReservation> getAllReservations() {
        return reservationRepository.findAllByOrderByReservationDateDesc();
    }
}