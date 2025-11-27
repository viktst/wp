package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    List<BookReservation> findAllByOrderByReservationDateDesc();

    @Query("SELECT br FROM BookReservation br WHERE LOWER(br.bookTitle) LIKE LOWER(CONCAT('%', :bookTitle, '%'))")

    List<BookReservation> findByBookTitleContainingIgnoreCase(String bookTitle);
}