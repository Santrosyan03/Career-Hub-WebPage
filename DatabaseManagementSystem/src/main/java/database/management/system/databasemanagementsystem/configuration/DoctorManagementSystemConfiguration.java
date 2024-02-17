package database.management.system.databasemanagementsystem.configuration;

import database.management.system.databasemanagementsystem.repository.DatabaseManagementSystemRepository;
import database.management.system.databasemanagementsystem.service.DatabaseManagementSystemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorManagementSystemConfiguration {

    @Bean
    public DatabaseManagementSystemService databaseManagementSystemService(DatabaseManagementSystemRepository repository) {
        return new DatabaseManagementSystemService(repository);
    }
}
