package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.Book;
import edu.eci.dosw.tdd.library.user.User;
import edu.eci.dosw.tdd.library.loan.Loan;
import edu.eci.dosw.tdd.library.loan.LoanStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library(){
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }
    public boolean addBook(Book book) {
        // Si el libro es nulo o no tiene ISBN, lo rechazamos
        if (book == null || book.getIsbn() == null || book.getIsbn().isEmpty()) {
            return false;
        }
        
        // Si es válido, lo agregamos al mapa sumando 1 a la cantidad
        books.put(book, books.getOrDefault(book, 0) + 1);
        return true;
    }

    public Loan loanABook(String userId, String isbn) {
        Book book = validateBook(isbn);
        User user = validateUser(userId);

        if (book == null || user == null) {return null;}

        Integer available = books.get(book);
        if (available == null || available <= 0) {return null;}

        for (Loan loan : loans) {
            if (loan.getUser() != null
                    && loan.getBook() != null
                    && loan.getStatus() == LoanStatus.ACTIVE
                    && loan.getUser().getId().equals(userId)
                    && loan.getBook().getIsbn().equals(isbn)) {
                return null;
            }
        }

        books.put(book, available - 1);

        Loan loan = new Loan(user, book);
        loans.add(loan);

        return loan;
    }

    public User validateUser(String userId){
        for(User u: users){
            if (u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }

    public Book validateBook(String isbn){
        for (Map.Entry<Book, Integer> entry : books.entrySet()) {
            if (entry.getKey().getIsbn().equals(isbn)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean addUser(User user) {
    if (user == null || user.getId() == null || user.getId().isEmpty()) {
        return false;
    }
    users.add(user);
    return true;
}

}
