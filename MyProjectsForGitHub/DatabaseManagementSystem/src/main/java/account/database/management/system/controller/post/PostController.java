package account.database.management.system.controller.post;

import account.database.management.system.model.Account;
import account.database.management.system.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class PostController {
    private final ServiceImpl service;

    @Autowired
    public PostController(ServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public Account addAccount(@RequestBody Account newAccount) {
        newAccount.setId(UUID.randomUUID());
        return service.addAccount(newAccount);
    }
}
