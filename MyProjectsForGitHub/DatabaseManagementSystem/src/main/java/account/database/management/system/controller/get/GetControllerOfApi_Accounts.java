package account.database.management.system.controller.get;

import account.database.management.system.service.impl.ServiceImpl;
import account.database.management.system.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class GetControllerOfApi_Accounts {
    private final ServiceImpl service;

    @Autowired
    public GetControllerOfApi_Accounts(ServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/get/{id}")
    public Account getAccountByID(@PathVariable UUID id) {
        return service.getAccountByID(id);
    }
}
