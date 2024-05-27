package account.database.management.system.controller.job.seeker.post;

import account.database.management.system.model.JobSeeker;
import account.database.management.system.service.impl.JobSeekerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/job/seeker")
public class PostControllerOfJobSeeker {
    private final JobSeekerImpl service;

    @Autowired
    public PostControllerOfJobSeeker(JobSeekerImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public JobSeeker addAccount(@RequestBody JobSeeker newJobSeeker) {
        newJobSeeker.setId(UUID.randomUUID());
        return service.add(newJobSeeker);
    }
}
