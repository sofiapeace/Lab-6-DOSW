package edu.eci.dosw.tdd.library.loan;

import edu.eci.dosw.tdd.library.book.book;
import edu.eci.dosw.tdd.library.user.user;
import java.time.LocalDateTime;

public class loan {
    private book book;
    private user user;
    private LocalDateTime loanDate;
    private loanStatus status;
    private LocalDateTime returnDate;

    public book getBook() { return book; }
    public void setBook(book book) { this.book = book; }

    public user getUser() { return user; }
    public void setUser(user user) { this.user = user; }

    public LocalDateTime getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDateTime loanDate) { this.loanDate = loanDate; }

    public loanStatus getStatus() { return status; }
    public void setStatus(loanStatus status) { this.status = status; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
}