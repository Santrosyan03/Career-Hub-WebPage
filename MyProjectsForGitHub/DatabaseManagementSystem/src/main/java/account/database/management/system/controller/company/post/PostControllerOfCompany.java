package account.database.management.system.controller.company.post;

import account.database.management.system.DTO.CompanyDTO;
import account.database.management.system.DTO.CredentialsDTO;
import account.database.management.system.DTO.SignUpCompanyDTO;
import account.database.management.system.config.UserAuthenticationProvider;
import account.database.management.system.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
public class PostControllerOfCompany {
    private final AccountService service;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<CompanyDTO> register(@RequestBody @Valid SignUpCompanyDTO user) {
        CompanyDTO createdUser = service.registerCompany(user);
        createdUser.setToken(userAuthenticationProvider.createCompanyToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<CompanyDTO> login(@RequestBody @Valid CredentialsDTO credentialsDto) {
        CompanyDTO userDto = service.loginCompany(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createCompanyToken(userDto));
        return ResponseEntity.ok(userDto);
    }
}
