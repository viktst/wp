package mk.ukim.finki.wp.lab.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String search,
                               Model model) {

        try {
            List<Book> books;
            if (search != null && !search.trim().isEmpty()) {
                books = bookService.searchBooks(search);
            } else {
                books = bookService.findAll();
            }

            model.addAttribute("books", books);
            model.addAttribute("search", search);

            if (error != null) {
                model.addAttribute("error", error);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error loading books: " + e.getMessage());
        }

        return "listBooks";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        try {
            model.addAttribute("authors", authorService.findAllOrderedByName());
            model.addAttribute("book", new Book());
            return "bookForm";
        } catch (Exception e) {
            return "redirect:/books?error=Error loading form: " + e.getMessage();
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Optional<Book> book = bookService.findById(id);
            if (book.isPresent()) {
                model.addAttribute("book", book.get());
                model.addAttribute("authors", authorService.findAllOrderedByName());
                return "bookForm";
            } else {
                return "redirect:/books?error=Book not found";
            }
        } catch (Exception e) {
            return "redirect:/books?error=Error loading book: " + e.getMessage();
        }
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {

        try {
            if (title == null || title.trim().isEmpty()) {
                return "redirect:/books?error=Title is required";
            }
            if (genre == null || genre.trim().isEmpty()) {
                return "redirect:/books?error=Genre is required";
            }
            if (averageRating == null || averageRating < 0 || averageRating > 5) {
                return "redirect:/books?error=Rating must be between 0 and 5";
            }

            if (id == null) {
                bookService.create(title.trim(), genre.trim(), averageRating, authorId);
            } else {
                bookService.update(id, title.trim(), genre.trim(), averageRating, authorId);
            }
            return "redirect:/books";

        } catch (Exception e) {
            return "redirect:/books?error=" + e.getMessage();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteById(id);
            return "redirect:/books";
        } catch (Exception e) {
            return "redirect:/books?error=" + e.getMessage();
        }
    }

    @GetMapping("/reserve/{id}")
    public String showReserveForm(@PathVariable Long id, Model model) {
        try {
            Optional<Book> book = bookService.findById(id);
            if (book.isPresent()) {
                model.addAttribute("book", book.get());
                return "reserveBook";
            } else {
                return "redirect:/books?error=Book not found";
            }
        } catch (Exception e) {
            return "redirect:/books?error=Error loading book: " + e.getMessage();
        }
    }
}