package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table (name = "clients")
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "client_id")
    private Long id;

    @Column (name = "first_name")
    private String fistName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "iban", unique = true)
    private String iban;

    private String address;

    @Column (name = "balance")
    private BigDecimal balance;

    @ManyToMany
    @JoinTable( name = "client_statuses",
            joinColumns = { @JoinColumn(name = "client_id")
            },
            inverseJoinColumns = { @JoinColumn(name = "status_id")
            }
    )
    private Set<Status> statuses = new HashSet<>();

    @OneToMany (mappedBy = "sender")
    @JsonBackReference
    private Set<Transaction> transactions = new HashSet<>();

}
