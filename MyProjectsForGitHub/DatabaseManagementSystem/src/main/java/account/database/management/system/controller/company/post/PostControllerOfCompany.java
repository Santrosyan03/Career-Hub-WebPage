package account.database.management.system.controller.job.seeker.post;

import account.database.management.system.model.Company;
import account.database.management.system.model.JobSeeker;
import account.database.management.system.service.impl.AccountImpl;
import account.database.management.system.service.impl.CompanyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class PostControllerOfCompany {
    private final CompanyImpl service;

    @Autowired
    public PostControllerOfCompany(CompanyImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Company addCompany(@RequestBody Company company) {
        company.setId(UUID.randomUUID());
        return service.add(company);
    }
}
