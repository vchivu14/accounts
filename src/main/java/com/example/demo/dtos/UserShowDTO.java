package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserShowDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private Double accountAmount;
}
