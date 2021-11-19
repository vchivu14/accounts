package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Number", nullable = false, length = 45)
    private String number;

    @Column(name = "Amount", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "Users_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account")
    private List<Transactions> transactions;

    public Account(Integer id, String number, Double amount, User user) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.user = user;
    }
}
