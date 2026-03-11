package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.book;
import java.util.HashMap;
import java.util.Map;

public class library {

    private Map<book, Integer> books = new HashMap<>();

    public boolean addBook(book book) {
        // Si el libro es nulo o no tiene ISBN, lo rechazamos
        if (book == null || book.getIsbn() == null || book.getIsbn().isEmpty()) {
            return false;
        }
        
        // Si es válido, lo agregamos al mapa sumando 1 a la cantidad
        books.put(book, books.getOrDefault(book, 0) + 1);
        return true;
    }
}
