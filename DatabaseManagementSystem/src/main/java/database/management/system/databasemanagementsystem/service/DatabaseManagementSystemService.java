package database.management.system.databasemanagementsystem.service;

import database.management.system.databasemanagementsystem.repository.DatabaseManagementSystemRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class DatabaseManagementSystemService {

    private final DatabaseManagementSystemRepository repository;

    public DatabaseManagementSystemService(DatabaseManagementSystemRepository repository) {
        this.repository = repository;
    }

    public String getString() {
        return "Hello";
    }

}
