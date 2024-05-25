package account.database.management.system.service.impl;

import account.database.management.system.exception.NotExistingErrorResponse;
import account.database.management.system.exception.RepeatedEmailErrorResponse;
import account.database.management.system.exception.RepeatedPasswordErrorResponse;
import account.database.management.system.model.Company;
import account.database.management.system.repository.CompanyJPARepository;
import account.database.management.system.service.AccountRepository;

import net.javaguides.examples.security.AESEncryptionDecryption;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class CompanyImpl implements AccountRepository<Company> {

    private final CompanyJPARepository companyRepository;

    @Autowired
    public CompanyImpl(CompanyJPARepository repository) {
        this.companyRepository = repository;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company add(Company company) {
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new RepeatedEmailErrorResponse();
        }

        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        String secretKey = "companySecretKey";
        String hashed = aesEncryptionDecryption.encrypt(company.getPassword(), secretKey);


        List<Company> companies = companyRepository.findAll();
        for (Company existingCompanies : companies) {
            if (aesEncryptionDecryption.decrypt(existingCompanies.getPassword(), secretKey).equals(company.getPassword())) {
                throw new RepeatedPasswordErrorResponse();
            }
        }
        company.setPassword(hashed);
        return companyRepository.save(company);
    }

    @Override
    public Company deleteByID(UUID id) throws NotExistingErrorResponse {
        Company companyToBeRemoved = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not exist with id: " + id));
        if (companyRepository.existsById(companyToBeRemoved.getId())) {
            companyRepository.deleteById(id);
            return companyToBeRemoved;
        } else {
            throw new NotExistingErrorResponse();
        }
    }

    @Override
    public Company updateByID(UUID id, Company updatedCompany) throws NotExistingErrorResponse {
        if (companyRepository.existsById(updatedCompany.getId())) {
            return companyRepository.findById(id)
                    .map(account -> {
                        account.setEmail(updatedCompany.getEmail());
                        account.setPassword(updatedCompany.getPassword());
                        return companyRepository.save(account);
                    })
                    .orElseThrow(() -> new RuntimeException("Company not found with id " + id));
        } else {
            throw new NotExistingErrorResponse();
        }
    }

    @Override
    public Company getByID(UUID id) throws NotExistingErrorResponse {
        Company companyToBeReturned = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not exist with id: " + id));
        if (!companyRepository.existsById(companyToBeReturned.getId())) {
            throw new NotExistingErrorResponse();
        }
        companyRepository.getReferenceById(id);
        return companyToBeReturned;
    }
}
