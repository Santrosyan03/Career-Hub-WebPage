package account.database.management.system.service.impl;

import account.database.management.system.exception.NotExistingErrorResponse;
import account.database.management.system.exception.RepeatedEmailErrorResponse;
import account.database.management.system.exception.RepeatedPasswordErrorResponse;
import account.database.management.system.model.Account;
import account.database.management.system.repository.AccountRepository;
import account.database.management.system.service.ServiceRep;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ServiceImpl implements ServiceRep {

    private final AccountRepository accountRepository;

    @Autowired
    public ServiceImpl(AccountRepository repository) {
        this.accountRepository = repository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account addAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RepeatedEmailErrorResponse();
        }

        List<Account> accounts = accountRepository.findAll();
        for (Account existingAccount : accounts) {
            if (existingAccount.getPassword().equals(account.getPassword())) {
                throw new RepeatedPasswordErrorResponse();
            }
        }

        return accountRepository.save(account);
    }

    @Override
    public Account deleteAccountByID(UUID id) throws NotExistingErrorResponse {
        Account accountToBeRemoved = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        if (accountRepository.existsById(accountToBeRemoved.getId())) {
            accountRepository.deleteById(id);
            return accountToBeRemoved;
        } else {
            throw new NotExistingErrorResponse();
        }
    }

    @Override
    public Account updateAccountByID(UUID id, Account updatedAccount) throws NotExistingErrorResponse {
        if (accountRepository.existsById(updatedAccount.getId())) {
            return accountRepository.findById(id)
                    .map(account -> {
                        account.setEmail(updatedAccount.getEmail());
                        account.setPassword(updatedAccount.getPassword());
                        return accountRepository.save(account);
                    })
                    .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
        } else {
            throw new NotExistingErrorResponse();
        }
    }

    @Override
    public Account getAccountByID(UUID id) throws NotExistingErrorResponse {
        Account accountToBeReturned = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        if (!accountRepository.existsById(accountToBeReturned.getId())) {
            throw new NotExistingErrorResponse();
        }
        accountRepository.getReferenceById(id);
        return accountToBeReturned;
    }
}
