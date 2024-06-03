package account.database.management.system.config;

import account.database.management.system.DTO.CompanyDTO;
import account.database.management.system.DTO.JobSeekerDTO;
import account.database.management.system.model.Company;
import account.database.management.system.model.JobSeeker;
import account.database.management.system.repository.CompanyJPARepository;
import account.database.management.system.repository.JobSeekerJPARepository;
import account.database.management.system.service.AccountService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final AccountService accountService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createJobSeekerToken(JobSeekerDTO user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("email", user.getEmail())
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .withClaim("dateOfBirth", user.getDateOfBirth())
                .withClaim("gender", user.getGender())
                .sign(algorithm);
    }

    public Authentication validateJobSeekerToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        JobSeekerDTO user = JobSeekerDTO.builder()
                .email(decoded.getClaim("email").asString())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .dateOfBirth(decoded.getClaim("dateOfBirth").asString())
                .gender(decoded.getClaim("gender").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateJobSeekerTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        JobSeekerDTO user = accountService.findJobSeekerByLogin(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public String createCompanyToken(CompanyDTO user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("companyName", user.getCompanyName())
                .withClaim("contactPersonFullName", user.getContactPersonFullName())
                .withClaim("country", user.getCountry())
                .withClaim("city", user.getCity())
                .withClaim("phoneNumber", user.getPhoneNumber())
                .withClaim("industry", user.getIndustry())
                .sign(algorithm);
    }

    public Authentication validateCompanyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        CompanyDTO user = CompanyDTO.builder()
                .companyName(decoded.getClaim("companyName").asString())
                .contactPersonFullName(decoded.getClaim("contactPersonFullName").asString())
                .country(decoded.getClaim("country").asString())
                .city(decoded.getClaim("city").asString())
                .phoneNumber(decoded.getClaim("phoneNumber").asString())
                .industry(decoded.getClaim("industry").asString())
                .email(decoded.getClaim("email").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateCompanyTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        CompanyDTO user = accountService.findCompanyByLogin(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
