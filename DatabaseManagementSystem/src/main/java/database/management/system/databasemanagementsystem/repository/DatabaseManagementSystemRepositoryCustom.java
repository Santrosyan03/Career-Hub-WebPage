package database.management.system.databasemanagementsystem.repository;

import database.management.system.databasemanagementsystem.model.Doctor;

import java.util.List;

public interface DatabaseManagementSystemRepositoryCustom {
    String getString();
    List<Doctor> findDoctorsById(String specialty);
}
