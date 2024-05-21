package account.database.management.system.service.impl;

import account.database.management.system.exception.NotExistingID;
import account.database.management.system.exception.RepeatedIdErrorResponse;
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
    public Account addAccount(Account account) throws RepeatedIdErrorResponse {
        if (!accountRepository.existsById(account.getId())) {
            return accountRepository.save(account);
        } else {
            throw new RepeatedIdErrorResponse();
        }
    }

    @Override
    public Account deleteAccountByID(UUID id) throws NotExistingID {
        Account accountToBeRemoved = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        if (accountRepository.existsById(accountToBeRemoved.getId())) {
            accountRepository.deleteById(id);
            return accountToBeRemoved;
        } else {
            throw new NotExistingID();
        }
    }

    @Override
    public Account updateAccountByID(UUID id, Account updatedAccount) throws NotExistingID {
        if (accountRepository.existsById(updatedAccount.getId())) {
            return accountRepository.findById(id)
                    .map(account -> {
                        account.setEmail(updatedAccount.getEmail());
                        account.setPassword(updatedAccount.getPassword());
                        return accountRepository.save(account);
                    })
                    .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
        } else {
            throw new NotExistingID();
        }
    }

    @Override
    public Account getAccountByID(UUID id) throws NotExistingID {
        Account accountToBeReturned = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        if (!accountRepository.existsById(accountToBeReturned.getId())) {
            throw new NotExistingID();
        }
        accountRepository.getReferenceById(id);
        return accountToBeReturned;
    }
}
