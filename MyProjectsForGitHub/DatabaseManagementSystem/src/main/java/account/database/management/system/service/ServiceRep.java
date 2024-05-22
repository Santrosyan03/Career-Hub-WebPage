package account.database.management.system.service;

import account.database.management.system.exception.NotExistingErrorResponse;
import account.database.management.system.exception.RepeatedEmailErrorResponse;
import account.database.management.system.model.Account;

import java.util.List;
import java.util.UUID;

public interface ServiceRep {
    List<Account> getAllAccounts();
    Account addAccount(Account account) throws Exception;
    Account deleteAccountByID(UUID id) throws NotExistingErrorResponse;
    Account updateAccountByID(UUID id, Account account) throws NotExistingErrorResponse;
    Account getAccountByID(UUID id) throws NotExistingErrorResponse;
}
