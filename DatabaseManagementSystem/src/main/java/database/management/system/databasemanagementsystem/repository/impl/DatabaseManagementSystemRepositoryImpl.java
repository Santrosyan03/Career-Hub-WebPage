package database.management.system.databasemanagementsystem.repository.impl;

import database.management.system.databasemanagementsystem.model.Doctor;
import database.management.system.databasemanagementsystem.repository.DatabaseManagementSystemRepositoryCustom;
import database.management.system.databasemanagementsystem.service.DatabaseManagementSystemService;

import java.util.List;

public class DatabaseManagementSystemRepositoryImpl implements DatabaseManagementSystemRepositoryCustom {

    private final DatabaseManagementSystemService databaseManagementSystemService;

    public DatabaseManagementSystemRepositoryImpl(DatabaseManagementSystemService databaseManagementSystemService) {
        this.databaseManagementSystemService = databaseManagementSystemService;
    }

    @Override
    public String getString() {
        return "hello";
    }

    @Override
    public List<Doctor> findDoctorsById(String specialty) {
        return null;
    }
}
