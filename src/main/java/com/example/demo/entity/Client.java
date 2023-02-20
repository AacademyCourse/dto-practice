package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String iban;
    private String address;
    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToMany()
    @JoinTable(
            name = "client_statuses",
            joinColumns = {@JoinColumn (name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "status_id")})
    private Set<Status> statuses;

    @OneToMany(mappedBy = "sender")
    @JsonBackReference
    private Set<Transaction> transactions;


}
