package com.example.demo.repos;

import com.example.demo.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transactions, Integer> {
    List<Transactions> findAllByAccount_Id(int accountId);
}
