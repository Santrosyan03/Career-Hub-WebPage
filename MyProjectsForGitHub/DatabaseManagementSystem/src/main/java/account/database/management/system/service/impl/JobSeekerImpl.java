package account.database.management.system.service.impl;

import account.database.management.system.exception.global.NotExistingErrorResponse;
import account.database.management.system.exception.global.RepeatedEmailErrorResponse;
import account.database.management.system.exception.global.RepeatedPasswordErrorResponse;
import account.database.management.system.model.JobSeeker;
import account.database.management.system.repository.JobSeekerJPARepository;
import account.database.management.system.service.AccountRepository;

import net.javaguides.examples.security.AESEncryptionDecryption;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class JobSeekerImpl implements AccountRepository<JobSeeker> {

    private final JobSeekerJPARepository jobSeekerRepository;

    @Autowired
    public JobSeekerImpl(JobSeekerJPARepository repository) {
        this.jobSeekerRepository = repository;
    }

    @Override
    public List<JobSeeker> getAll() {
        return jobSeekerRepository.findAll();
    }

    @Override
    public JobSeeker add(JobSeeker jobSeeker) {
        if (jobSeekerRepository.existsByEmail(jobSeeker.getEmail())) {
            throw new RepeatedEmailErrorResponse("Job Seeker");
        }

        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        String secretKey = "JobSeekerSecretKey";
        String hashed = aesEncryptionDecryption.encrypt(jobSeeker.getPassword(), secretKey);


        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
        for (JobSeeker existingJobSeeker : jobSeekers) {
            if (aesEncryptionDecryption.decrypt(existingJobSeeker.getPassword(), secretKey).equals(jobSeeker.getPassword())) {
                throw new RepeatedPasswordErrorResponse("Job Seeker");
            }
        }
        jobSeeker.setPassword(hashed);
        return jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public JobSeeker deleteByID(UUID id) throws NotExistingErrorResponse {
        JobSeeker jobSeekerToBeRemoved = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        if (jobSeekerRepository.existsById(jobSeekerToBeRemoved.getId())) {
            jobSeekerRepository.deleteById(id);
            return jobSeekerToBeRemoved;
        } else {
            throw new NotExistingErrorResponse("Job Seeker");
        }
    }

    @Override
    public JobSeeker updateByID(UUID id, JobSeeker updatedJobSeeker) throws NotExistingErrorResponse {
        if (jobSeekerRepository.existsById(updatedJobSeeker.getId())) {
            return jobSeekerRepository.findById(id)
                    .map(account -> {
                        account.setEmail(updatedJobSeeker.getEmail());
                        account.setPassword(updatedJobSeeker.getPassword());
                        return jobSeekerRepository.save(account);
                    })
                    .orElseThrow(() -> new RuntimeException("JobSeeker not found with id " + id));
        } else {
            throw new NotExistingErrorResponse("Job Seeker");
        }
    }

    @Override
    public JobSeeker getByID(UUID id) throws NotExistingErrorResponse {
        JobSeeker jobSeekerToBeReturned = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        if (!jobSeekerRepository.existsById(jobSeekerToBeReturned.getId())) {
            throw new NotExistingErrorResponse("Job Seeker");
        }
        jobSeekerRepository.getReferenceById(id);
        return jobSeekerToBeReturned;
    }
}
