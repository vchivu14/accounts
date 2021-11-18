package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TransferDTO {
    private Integer userSenderId;
    private Integer userReceiverId;
    private Double amount;
}
