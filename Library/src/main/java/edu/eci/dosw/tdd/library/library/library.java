package edu.eci.dosw.tdd.library.library;

import edu.eci.dosw.tdd.library.book.book;
import edu.eci.dosw.tdd.library.loan.loan;
import edu.eci.dosw.tdd.library.user.user;

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
        return false;
    }

    public loan loanABook(String userId, String isbn) {
        return null;
    }

    public loan returnLoan(loan loan) {
        return null;
    }

    public boolean addUser(user user) {
        return users.add(user);
    }
}