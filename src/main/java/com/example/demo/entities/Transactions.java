package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "Other_Account", nullable = false)
    private int receiverAccountId;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "Accounts_id", referencedColumnName = "id", nullable = false)
    private Account account;

    public Transactions(int receiverAccountId, double amount, Account account) {
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.account = account;
    }

}
