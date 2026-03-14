package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.book.Book;
import edu.eci.dosw.tdd.loan.Loan;
import edu.eci.dosw.tdd.loan.LoanStatus;
import edu.eci.dosw.tdd.user.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class LibraryTest {

    private Library library;
    private User user1;
    private User user2;
    private Book newBook;

    @Test
    public void addBook_ShouldReturnTrue_WhenBookIsNew() {
        Library library = new Library();
        Book book = new Book("Cien Años de Soledad", "Gabriel García Márquez", "12345");
        boolean result = library.addBook(book);
        assertTrue(result);
    }

    @Test
    public void returnLoan_ShouldReturnLoanWithReturnedStatus_WhenLoanExists() {
        Library library = new Library();
        User user = new User();
        user.setId("user1");
        user.setName("Hildebrando");
        library.addUser(user);
        Book book = new Book("Cien Años de Soledad", "Gabriel García Márquez", "12345");
        library.addBook(book);
        Loan loan = library.loanABook("user1", "12345");
        Loan returned = library.returnLoan(loan);
        assertNotNull(returned);
        assertEquals(LoanStatus.RETURNED, returned.getStatus());
        assertNotNull(returned.getReturnDate());
    }

    @Test
    public void addBook_ShouldReturnFalse_WhenBookHasNoIsbn() {
        Library library = new Library();
        Book invalidBook = new Book("Libro sin código", "Autor Anónimo", "");
        boolean result = library.addBook(invalidBook);
        assertFalse(result);
    }

    @Test
    public void addBook_ShouldReturnFalse_WhenBookIsNull() {
        Library library = new Library();
        boolean result = library.addBook(null);
        assertFalse(result);
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
        newBook = new Book("Clean Code", "Robert C. Martin", "ISBN-001");
    }

    @Test
    void shouldFailWhenUserAlreadyHasAnActiveLoanForTheSameBook() {
        library.addUser(user1);
        library.addBook(newBook);
        library.addBook(newBook);
        Loan firstLoan = library.loanABook("123", "ISBN-001");
        Loan secondLoan = library.loanABook("123", "ISBN-001");
        assertNotNull(firstLoan);
        assertNull(secondLoan);
    }

    @Test
    void shouldFailWhenUserIsNotFound() {
        library.addBook(newBook);
        Loan loan = library.loanABook("999", "ISBN-001");
        assertNull(loan);
    }

    @Test
    void shouldFailWhenBookIsNotFound() {
        library.addUser(user1);
        Loan loan = library.loanABook("123", "ISBN-999");
        assertNull(loan);
    }

    @Test
    void book_Equals_ShouldReturnTrue_WhenSameIsbn() {
        Book newBook = new Book("Título1", "Autor1", "ISBN123");
        Book book2 = new Book("Título2", "Autor2", "ISBN123");
        assertEquals(newBook, book2);
    }

    @Test
    void book_Equals_ShouldReturnFalse_WhenDifferentIsbn() {
        Book newBook = new Book("Título", "Autor", "ISBN123");
        Book book2 = new Book("Título", "Autor", "ISBN456");
        assertNotEquals(newBook, book2);
    }

    @Test
    void book_Equals_ShouldReturnFalse_WhenNull() {
        Book book = new Book("Título", "Autor", "ISBN123");
        assertNotEquals(book, null);
    }

    @Test
    void book_HashCode_ShouldBeConsistentWithEquals() {
        Book newBook = new Book("Título1", "Autor1", "ISBN123");
        Book book2 = new Book("Título2", "Autor2", "ISBN123");
        assertEquals(newBook.hashCode(), book2.hashCode());
    }

    @Test
    void addUser_ShouldReturnTrue_WhenUserIsValid() {
        User user = new User();
        user.setId("123");
        user.setName("Test User");
        assertTrue(library.addUser(user));
    }

    @Test
    void addUser_ShouldReturnFalse_WhenUserIsNull() {
        assertFalse(library.addUser(null));
    }

    @Test
    void addUser_ShouldReturnFalse_WhenUserIdIsNull() {
        User user = new User();
        user.setId(null);
        assertFalse(library.addUser(user));
    }

    @Test
    void addUser_ShouldReturnFalse_WhenUserIdIsEmpty() {
        User user = new User();
        user.setId("");
        assertFalse(library.addUser(user));
    }

    @Test
    void validateUser_ShouldReturnNull_WhenUserNotFound() {
        assertNull(library.validateUser("usuario-inexistente"));
    }

    @Test
    void validateBook_ShouldReturnNull_WhenBookNotFound() {
        assertNull(library.validateBook("ISBN-inexistente"));
    }

    @Test
    void fullLoanAndReturnFlow_ShouldWorkCorrectly() {
        library.addUser(user1);
        library.addBook(newBook);

        Loan loan = library.loanABook("123", "ISBN-001");

        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertNotNull(loan.getLoanDate());

        Loan returned = library.returnLoan(loan);

        assertSame(loan, returned);
        assertEquals(LoanStatus.RETURNED, returned.getStatus());
        assertNotNull(returned.getReturnDate());
        assertTrue(returned.getReturnDate().isAfter(loan.getLoanDate()));
    }

    @Test
    void loanABook_ShouldReturnNull_WhenBookIsNull() {
        library.addUser(user1);
        assertNull(library.loanABook("123", "ISBN-inexistente"));
    }

    @Test
    void loanABook_ShouldReturnNull_WhenUserIsNull() {
        library.addBook(newBook);
        assertNull(library.loanABook("usuario-inexistente", "ISBN-001"));
    }

    @Test
    void returnLoan_ShouldReturnNull_WhenLoanNotInList() {
        Loan fakeLoan = new Loan(user1, newBook);
        assertNull(library.returnLoan(fakeLoan));
    }

    @Test
    void user_GettersAndSetters_ShouldWorkCorrectly() {
        User testUser = new User();
        testUser.setId("test-id");
        testUser.setName("Test Name");
        assertEquals("test-id", testUser.getId());
        assertEquals("Test Name", testUser.getName());
    }

    @Test
    void validateUser_ShouldFindUser_WhenIdMatches() {
        library.addUser(user1);
        library.addUser(user2);
        User result = library.validateUser("456");
        assertNotNull(result);
        assertEquals("Maria", result.getName());
    }

    @Test
    void validateBook_ShouldFindBook_WhenIsbnMatches() {
        library.addBook(newBook);
        Book result = library.validateBook("ISBN-001");
        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
    }
        
    @Test
    void loan_Constructor_ShouldSetCorrectValues() {
        User user = new User();
        user.setId("999");
        Book book = new Book("Test", "Author", "TEST-999");
        Loan loan = new Loan(user, book);
        
        assertEquals(user, loan.getUser());
        assertEquals(book, loan.getBook());
        assertNotNull(loan.getLoanDate());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }

    @Test
    void loan_SettersAndGetters_ShouldWork() {
        User user = new User();
        user.setId("888");
        Book book = new Book("Test2", "Author2", "TEST-888");
        Loan loan = new Loan(user, book);
        
        User newUser = new User();
        newUser.setId("777");
        loan.setUser(newUser);
        assertEquals("777", loan.getUser().getId());
        
        Book newBook = new Book("New", "New Author", "NEW-001");
        loan.setBook(newBook);
        assertEquals("NEW-001", loan.getBook().getIsbn());
    }
}