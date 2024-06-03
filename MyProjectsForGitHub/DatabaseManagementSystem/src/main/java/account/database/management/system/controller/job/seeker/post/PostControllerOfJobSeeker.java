package account.database.management.system.controller.job.seeker.post;

import account.database.management.system.DTO.*;
import account.database.management.system.config.UserAuthenticationProvider;
import account.database.management.system.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/job/seeker")
public class PostControllerOfJobSeeker {
    private final AccountService service;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<JobSeekerDTO> register(@RequestBody @Valid SignUpJobSeekerDTO user) {
        JobSeekerDTO createdUser = service.registerJobSeeker(user);
        createdUser.setToken(userAuthenticationProvider.createJobSeekerToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JobSeekerDTO> login(@RequestBody @Valid CredentialsDTO credentialsDto) {
        JobSeekerDTO userDto = service.loginJobSeeker(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createJobSeekerToken(userDto));
        return ResponseEntity.ok(userDto);
    }
}
