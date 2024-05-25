package account.database.management.system.service;

import account.database.management.system.exception.NotExistingErrorResponse;
import account.database.management.system.model.JobSeeker;

import java.util.List;
import java.util.UUID;

public interface AccountRepository<T> {
    List<T> getAll();
    T add(T element) throws Exception;
    T deleteByID(UUID id) throws NotExistingErrorResponse;
    T updateByID(UUID id, T element) throws NotExistingErrorResponse;
    T getByID(UUID id) throws NotExistingErrorResponse;
}
