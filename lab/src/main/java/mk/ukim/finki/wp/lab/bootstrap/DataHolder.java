package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    static {
        authors.add(new Author(1L, "Robert", "Martin", "USA", "Software engineer and author"));
        authors.add(new Author(2L, "Steve", "Stevenson", "UK", "English writer and philologist"));
        authors.add(new Author(3L, "George", "Georges", "UK", "Novelist and essayist"));

        books.add(new Book(1L, "The art of functional programming", "Programming", 4.2, authors.get(0)));
        books.add(new Book(2L, "Cracking the coding interview", "Career", 4.5, authors.get(0)));
        books.add(new Book(3L, "1984", "Dystopian", 4.6, authors.get(2)));
        books.add(new Book(4L, "Domain Driven Design", "Software Architecture", 4.4, authors.get(0)));
        books.add(new Book(5L, "The Hobbit", "Fantasy", 4.7, authors.get(1)));
        books.add(new Book(6L, "Harry Potter", "Fantasy", 4.8, authors.get(1)));
        books.add(new Book(7L, "Clean Code", "Programming", 4.0, authors.get(0)));
        books.add(new Book(8L, "Lord of the Rings", "Fantasy", 4.9, authors.get(1)));
        books.add(new Book(9L, "Pragmatic Programmer", "Programming", 4.3, authors.get(0)));
        books.add(new Book(10L, "The Alchemist", "Adventure", 4.1, authors.get(2)));
    }
}