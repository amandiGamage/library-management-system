package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Borrower borrower;

    @OneToOne
    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;
}
