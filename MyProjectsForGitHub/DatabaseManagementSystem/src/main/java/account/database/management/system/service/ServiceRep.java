package account.database.management.system.service;

import account.database.management.system.exception.NotExistingID;
import account.database.management.system.exception.RepeatedIdErrorResponse;
import account.database.management.system.model.Account;

import java.util.List;
import java.util.UUID;

public interface ServiceRep {
    List<Account> getAllAccounts();
    Account addAccount(Account account) throws RepeatedIdErrorResponse;
    Account deleteAccountByID(UUID id) throws NotExistingID;
    Account updateAccountByID(UUID id, Account account) throws NotExistingID;
    Account getAccountByID(UUID id) throws NotExistingID;
}
