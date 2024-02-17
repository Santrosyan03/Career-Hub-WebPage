package database.management.system.databasemanagementsystem.controller;

import database.management.system.databasemanagementsystem.service.DatabaseManagementSystemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DatabaseManagementSystemController {
    private final DatabaseManagementSystemService databaseManagementSystemService;

    public DatabaseManagementSystemController(DatabaseManagementSystemService databaseManagementSystemService) {
        this.databaseManagementSystemService = databaseManagementSystemService;
    }

    @GetMapping("/hello")
    public String hello() {
        return databaseManagementSystemService.getString();
    }
}
