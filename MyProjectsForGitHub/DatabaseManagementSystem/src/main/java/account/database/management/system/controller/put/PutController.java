package account.database.management.system.controller.put;

import account.database.management.system.service.impl.ServiceImpl;
import account.database.management.system.exception.NotExistingID;
import account.database.management.system.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class PutController {
    private final ServiceImpl service;

    @Autowired
    public PutController(ServiceImpl service) {
        this.service = service;
    }
    @PutMapping("/update/{id}")
    public Account updateAccount(@PathVariable UUID id, @RequestBody Account updatedAccount) throws NotExistingID {
        return service.updateAccountByID(id, updatedAccount);
    }

}
