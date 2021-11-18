package com.example.demo.controller;

import com.example.demo.dtos.TransferDTO;
import com.example.demo.dtos.UserShowDTO;
import com.example.demo.services.TransferServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    private TransferServices transferServices;

    public TransferController(TransferServices transferServices) {
        this.transferServices = transferServices;
    }

    @GetMapping
    public String getTransferPage(@ModelAttribute TransferDTO transferDTO,
                                  @ModelAttribute UserShowDTO userShowDTO,
                                  Model model) {
        model.addAttribute(transferDTO);
        model.addAttribute("u",userShowDTO);
        model.addAttribute("users", transferServices.getAllUsers());
        return "transfer";
    }

    @PostMapping
    public String makeTransaction(@ModelAttribute TransferDTO transferDTO) {
        transferServices.transferFunds(transferDTO);
        return "redirect:/transfer";
    }
}
