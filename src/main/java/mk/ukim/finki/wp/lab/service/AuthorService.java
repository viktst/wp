package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import java.util.List;
import java.util.Map;

public interface AuthorService {
    List<Author> findAll();
    List<Author> findAllOrderedByName();
    Map<Author, Map<String, Object>> getAuthorStatistics();
}