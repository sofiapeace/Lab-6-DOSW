package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void addBook_ShouldReturnTrue_WhenBookIsNew() {
        library library = new library();
        book book = new book("Cien Años de Soledad", "Gabriel García Márquez", "12345");
        boolean result = library.addBook(book);
        assertTrue(result, "Debería retornar true al añadir un libro nuevo");
    }
}