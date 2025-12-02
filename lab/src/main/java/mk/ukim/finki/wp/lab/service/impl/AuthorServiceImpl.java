package mk.ukim.finki.wp.lab.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAllOrderedByName() {
        return authorRepository.findAllByOrderByNameAscSurnameAsc();
    }

    @Override
    public Map<Author, Map<String, Object>> getAuthorStatistics() {
        List<Author> authors = authorRepository.findAll();
        Map<Author, Map<String, Object>> stats = new LinkedHashMap<>();

        for (Author author : authors) {
            List<Book> books = bookRepository.findAllByAuthor_Id(author.getId());
            int count = books.size();
            double avgRating = 0.0;
            if (count > 0) {
                double sum = 0;
                for (Book b : books) {
                    sum += (b.getAverageRating() != null ? b.getAverageRating() : 0);
                }
                avgRating = sum / count;
            }

            Map<String, Object> stat = new LinkedHashMap<>();
            stat.put("booksCount", count);
            stat.put("averageRating", avgRating);

            stats.put(author, stat);
        }

        return stats;
    }
}
