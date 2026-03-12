package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.book;
import edu.eci.dosw.tdd.library.loan.loan;
import edu.eci.dosw.tdd.library.loan.loanStatus;
import edu.eci.dosw.tdd.library.user.user;
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

    @Test
    public void returnLoan_ShouldReturnLoanWithReturnedStatus_WhenLoanExists() {
        // Arrange
        library library = new library();

        user user = new user();
        user.setId("user1");
        user.setName("Hildebrando");
        library.addUser(user);

        book book = new book("Cien Años de Soledad", "Gabriel García Márquez", "12345");
        library.addBook(book);

        loan loan = library.loanABook("user1", "12345");

        // Act
        loan returned = library.returnLoan(loan);

        // Assert
        assertNotNull(returned);
        assertEquals(loanStatus.RETURNED, returned.getStatus());
        assertNotNull(returned.getReturnDate());
    }
}