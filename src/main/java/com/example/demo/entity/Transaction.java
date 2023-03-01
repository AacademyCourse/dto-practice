package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JsonManagedReference
    private Client sender;
    @ManyToOne
    @JsonManagedReference
    private Client receiver;
    @ManyToOne
    private Currency currency;
    private Instant date;
    private String reason;
}
