package account.database.management.system.controller.job.seeker.post;

import account.database.management.system.model.Account;
import account.database.management.system.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/job/seeker")
public class PostController {
    private final ServiceImpl service;

    @Autowired
    public PostController(ServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Account addAccount(@RequestBody Account newAccount) throws Exception {
        newAccount.setId(UUID.randomUUID());
        return service.addAccount(newAccount);
    }
}
