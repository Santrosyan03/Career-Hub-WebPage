package account.database.management.system.service;

import account.database.management.system.DTO.*;
import account.database.management.system.exception.global.AppException;
import account.database.management.system.mappers.CompanyMapper;
import account.database.management.system.mappers.JobSeekerMapper;
import account.database.management.system.model.Company;
import account.database.management.system.model.JobSeeker;
import account.database.management.system.repository.CompanyJPARepository;
import account.database.management.system.repository.JobSeekerJPARepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final CompanyJPARepository companyRepository;

    private final JobSeekerJPARepository jobSeekerRepository;

    private final PasswordEncoder passwordEncoder;

    private final CompanyMapper companyMapper;

    private final JobSeekerMapper jobSeekerMapper;

    public CompanyDTO loginCompany(CredentialsDTO credentialsDto) {
        Company user = companyRepository.findCompanyByLogin(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return companyMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public CompanyDTO registerCompany(SignUpCompanyDTO userDto) {
        Optional<Company> optionalUser = companyRepository.findCompanyByLogin(userDto.email());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Company user = companyMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        Company savedUser = companyRepository.save(user);

        return companyMapper.toUserDto(savedUser);
    }

    public CompanyDTO findCompanyByLogin(String login) {
        Company user = companyRepository.findCompanyByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return companyMapper.toUserDto(user);
    }

    public JobSeekerDTO loginJobSeeker(CredentialsDTO credentialsDto) {
        JobSeeker user = jobSeekerRepository.findJobSeekerByLogin(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return jobSeekerMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public JobSeekerDTO registerJobSeeker(SignUpJobSeekerDTO userDto) {
        Optional<JobSeeker> optionalUser = jobSeekerRepository.findJobSeekerByLogin(userDto.email());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        JobSeeker user = jobSeekerMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

        JobSeeker savedUser = jobSeekerRepository.save(user);

        return jobSeekerMapper.toUserDto(savedUser);
    }

    public JobSeekerDTO findJobSeekerByLogin(String login) {
        JobSeeker user = jobSeekerRepository.findJobSeekerByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return jobSeekerMapper.toUserDto(user);
    }
}