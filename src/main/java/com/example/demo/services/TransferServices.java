package com.example.demo.services;

import com.example.demo.dtos.TransferDTO;
import com.example.demo.dtos.UserShowDTO;

import java.util.List;

public interface TransferServices {
    void transferFunds(TransferDTO transferDTO);
    UserShowDTO getUserShowDTO(Integer userId);
    List<UserShowDTO> getAllUsers();
}
