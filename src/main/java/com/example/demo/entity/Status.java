package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String statusName;

//    @ManyToMany
//    @JoinTable(
//            name = "client_statuses",
//            joinColumns = {@JoinColumn(name = "client_id")},
//            inverseJoinColumns = {@JoinColumn(name = "status_id")}
//    )
//    private Set<Client> clients;
}
