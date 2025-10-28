package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    static {
        books.add(new Book("The art of functional programming", "Programming", 4.2));
        books.add(new Book("Cracking the coding interview", "Career", 4.5));
        books.add(new Book("1984", "Dystopian", 4.6));
        books.add(new Book("Domain Driven Design", "Software Architecture", 4.4));
        books.add(new Book("The Hobbit", "Fantasy", 4.7));
        books.add(new Book("Harry Potter", "Fantasy", 4.8));
        books.add(new Book("Clean Code", "Programming", 4.0));
        books.add(new Book("Lord of the Rings", "Fantasy", 4.9));
        books.add(new Book("Pragmatic Programmer", "Programming", 4.3));
        books.add(new Book("The Alchemist", "Adventure", 4.1));
    }
}