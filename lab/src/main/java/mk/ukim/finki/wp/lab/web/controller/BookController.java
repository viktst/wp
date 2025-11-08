package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String searchText,
                               @RequestParam(required = false) String rating,
                               Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if (searchText != null && !searchText.isEmpty() || rating != null && !rating.isEmpty()) {
            Double ratingValue = rating != null && !rating.isEmpty() ? Double.parseDouble(rating) : null;
            model.addAttribute("books", bookService.searchBooks(searchText, ratingValue));
            model.addAttribute("searchText", searchText);
            model.addAttribute("rating", rating);
        } else {
            model.addAttribute("books", bookService.listAll());
        }

        return "listBooks";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", null);
        return "bookForm";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()) {
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", book.get());
        model.addAttribute("authors", authorService.findAll());
        return "bookForm";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.save(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}