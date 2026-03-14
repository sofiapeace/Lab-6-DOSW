package edu.eci.dosw.tdd.library;

import edu.eci.dosw.tdd.library.book.book;
import edu.eci.dosw.tdd.library.loan.loan;
import edu.eci.dosw.tdd.library.loan.loanStatus;
import edu.eci.dosw.tdd.library.user.user;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class library {

    private final List<user> users;
    private final Map<book, Integer> books;
    private final List<loan> loans;

    public library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    public boolean addBook(book book) {
        // Si el libro es nulo o no tiene ISBN, lo rechazamos
        if (book == null || book.getIsbn() == null || book.getIsbn().isEmpty()) {
            return false;
        }
        // Si es válido, lo agregamos al mapa sumando 1 a la cantidad
        books.put(book, books.getOrDefault(book, 0) + 1);
        return true;
    }

    public loan returnLoan(loan loan) {
        // validar que el prestamo existe en la lista
        if (!loans.contains(loan)) return null;

        // aumentar la cantidad del libro en 1
        books.put(loan.getBook(), books.get(loan.getBook()) + 1);

        // actualizar estado y fecha de devolucion
        loan.setStatus(loanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        return loan;
    }

    public boolean addUser(user user) {
        return users.add(user);
    }
}