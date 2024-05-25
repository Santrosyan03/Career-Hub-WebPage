package account.database.management.system.controller.job.seeker.post;

import account.database.management.system.model.JobSeeker;
import account.database.management.system.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/job/seeker")
public class PostControllerOfJobSeeker {
    private final AccountImpl service;

    @Autowired
    public PostControllerOfJobSeeker(AccountImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public JobSeeker addAccount(@RequestBody JobSeeker newJobSeeker) throws Exception {
        newJobSeeker.setId(UUID.randomUUID());
        return service.add(newJobSeeker);
    }
}
