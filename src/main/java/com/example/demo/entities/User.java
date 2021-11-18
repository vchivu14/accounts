package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "First_Name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "Last_Name", nullable = false, length = 45)
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    public User(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
