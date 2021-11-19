package com.example.demo.services;

import com.example.demo.dtos.TransactionDTO;
import com.example.demo.dtos.TransferDTO;
import com.example.demo.dtos.UserShowDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Transactions;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repos.AccountRepo;
import com.example.demo.repos.TransactionRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferServicesImp implements TransferServices {
    private AccountRepo accountRepo;
    private UserRepo userRepo;
    private TransactionRepo transactionRepo;

    public TransferServicesImp(AccountRepo accountRepo, UserRepo userRepo,
                               TransactionRepo transactionRepo) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.transactionRepo = transactionRepo;
    }

    private String errorMessage(Integer id){
        return "Resource Not found with id = " + id;
    }

    private User getUserById(Integer userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage(userId)));
    }

    @Override
    @Transactional
    public void transferFunds(TransferDTO transferDTO) {
        User userSender = getUserById(transferDTO.getUserSenderId());
        User userReceiver = getUserById(transferDTO.getUserReceiverId());

        Account accountSender = userSender.getAccounts().get(0);
        Account accountReceiver = userReceiver.getAccounts().get(0);

        accountSender.setAmount(accountSender.getAmount() - transferDTO.getAmount());
        accountRepo.save(accountSender);
        accountReceiver.setAmount(accountReceiver.getAmount() + transferDTO.getAmount());
        accountRepo.save(accountReceiver);

        transactionRepo.save(new Transactions(accountReceiver.getId(),transferDTO.getAmount(),accountSender));
    }

    private TransactionDTO getTransaction(Transactions transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        User user = accountRepo.getById(transaction.getReceiverAccountId()).getUser();
        String username = user.getFirstName().concat("_").concat(user.getLastName());
        transactionDTO.setReceiverUsername(username);
        Account account = accountRepo.getById(transaction.getReceiverAccountId());
        transactionDTO.setReceiverAccount(account.getNumber());
        return transactionDTO;
    }
    private List<TransactionDTO> getTransactions(int accountId) {
        List<Transactions> transactions = transactionRepo.findAllByAccount_Id(accountId);
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transactions t: transactions) {
            transactionDTOS.add(getTransaction(t));
        }
        return transactionDTOS;
    }
    @Override
    public UserShowDTO getUserShowDTO(Integer userId) {
        User user = getUserById(userId);
        Account userAccount = user.getAccounts().get(0);
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(user.getId());
        userShowDTO.setFirstName(user.getFirstName());
        userShowDTO.setLastName(user.getLastName());
        userShowDTO.setAccountNumber(userAccount.getNumber());
        userShowDTO.setAccountAmount(userAccount.getAmount());
        userShowDTO.setTransactionsList(getTransactions(userAccount.getId()));
        return userShowDTO;
    }

    @Override
    public List<UserShowDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserShowDTO> userShowDTOS = new ArrayList<>();
        for (User u: users) {
            userShowDTOS.add(getUserShowDTO(u.getId()));
        }
        return userShowDTOS;
    }
}
