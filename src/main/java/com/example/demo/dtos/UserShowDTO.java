package com.example.demo.dtos;

import com.example.demo.entities.Transactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserShowDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private Double accountAmount;
    private List<TransactionDTO> transactionsList;
}
