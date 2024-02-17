package database.management.system.databasemanagementsystem.repository;

import database.management.system.databasemanagementsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseManagementSystemRepository extends JpaRepository<Doctor, Long> {
}
