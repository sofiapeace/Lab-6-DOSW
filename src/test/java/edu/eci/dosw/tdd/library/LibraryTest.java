package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;
import edu.eci.dosw.tdd.library.user.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

public class LibraryTest {

    private Library library;
    private User user1;
    private User user2;
    private Book book1;

    @Test
    public void addBook_ShouldReturnTrue_WhenBookIsNew() {
        Library library = new Library();
        Book book = new Book("Cien Años de Soledad", "Gabriel García Márquez", "12345");
        boolean result = library.addBook(book);
        assertTrue(result, "Debería retornar true al añadir un libro nuevo");
    }

    @Test
    public void returnLoan_ShouldReturnLoanWithReturnedStatus_WhenLoanExists() {
        // Arrange
        Library library = new Library();

        User user = new User();
        user.setId("user1");
        user.setName("Hildebrando");
        library.addUser(user);

        Book book = new Book("Cien Años de Soledad", "Gabriel García Márquez", "12345");
        library.addBook(book);

        Loan loan = library.loanABook("user1", "12345");

        // Act
        Loan returned = library.returnLoan(loan);

        // Assert
        assertNotNull(returned);
        assertEquals(LoanStatus.RETURNED, returned.getStatus());
        assertNotNull(returned.getReturnDate());
    }

    @Test
    public void addBook_ShouldReturnFalse_WhenBookHasNoIsbn() {
        // Arrange
        Library library = new Library();
        Book invalidBook = new Book("Libro sin código", "Autor Anónimo", "");

        // Act
        boolean result = library.addBook(invalidBook);

        // Assert
        assertFalse(result, "Debería retornar false al intentar añadir un libro sin ISBN");
    }

    @Test
    public void addBook_ShouldReturnFalse_WhenBookIsNull() {
        // Arrange
        Library library = new Library();
        
        // Act
        boolean result = library.addBook(null);

        // Assert
        assertFalse(result, "Debería retornar false al intentar añadir un libro nulo");
    }

    @BeforeEach
    void setUp() {
        library = new Library();

        user1 = new User();
        user1.setId("123");
        user1.setName("Diego");

        user2 = new User();
        user2.setId("456");
        user2.setName("Maria");

        book1 = new Book("Clean Code", "Robert C. Martin", "ISBN-001");
    }


    @Test
    void shouldFailWhenUserAlreadyHasAnActiveLoanForTheSameBook() {
        library.addUser(user1);
        library.addBook(book1);
        library.addBook(book1); 

        Loan firstLoan = library.loanABook("123", "ISBN-001");
        Loan secondLoan = library.loanABook("123", "ISBN-001");

        assertNotNull(firstLoan);
        assertNull(secondLoan);
    }

    @Test
    void shouldFailWhenUserIsNotFound() {
        library.addBook(book1);

        Loan loan = library.loanABook("999", "ISBN-001");

        assertNull(loan);
    }

    @Test
    void shouldFailWhenBookIsNotFound() {
        library.addUser(user1);

        Loan loan = library.loanABook("123", "ISBN-999");

        assertNull(loan);
    }

}