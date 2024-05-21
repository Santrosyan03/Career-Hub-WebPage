package account.database.management.system.controller.delete;

import account.database.management.system.exception.NotExistingID;
import account.database.management.system.model.Account;
import account.database.management.system.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class DeleteController {
    private final ServiceImpl service;

    @Autowired
    public DeleteController(ServiceImpl service) {
        this.service = service;
    }

    @DeleteMapping("/delete/{id}")
    public Account deleteAccountByID(@PathVariable UUID id) throws NotExistingID {
        return service.deleteAccountByID(id);
    }
}
