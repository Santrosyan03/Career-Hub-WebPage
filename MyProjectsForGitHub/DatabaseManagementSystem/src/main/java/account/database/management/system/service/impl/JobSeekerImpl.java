//package account.database.management.system.service.impl;
//
//import account.database.management.system.exception.global.*;
//import account.database.management.system.model.Company;
//import account.database.management.system.model.JobSeeker;
//import account.database.management.system.repository.CompanyJPARepository;
//import account.database.management.system.repository.JobSeekerJPARepository;
//import account.database.management.system.service.AccountRepository;
//
//import net.javaguides.examples.security.AESEncryptionDecryption;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//
//@Service
//public class JobSeekerImpl implements AccountRepository<JobSeeker> {
//
//    private final CompanyJPARepository companyRepository;
//    private final JobSeekerJPARepository jobSeekerRepository;
//    private final String COMPANY = "Company";
//    private final String JOB_SEEKER = "Job Seeker";
//    AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
//    String secretKey = "secretKey";
//
//
//
//    @Autowired
//    public JobSeekerImpl(JobSeekerJPARepository jobSeekerRepository, CompanyJPARepository companyRepository) {
//        this.jobSeekerRepository = jobSeekerRepository;
//        this.companyRepository = companyRepository;
//    }
//
//    @Override
//    public List<JobSeeker> getAll() {
//        return jobSeekerRepository.findAll();
//    }
//
//    @Override
//    public JobSeeker add(JobSeeker jobSeeker) {
//        if (jobSeekerRepository.existsByEmail(jobSeeker.getEmail())) {
//            throw new RepeatedEmailErrorResponse(JOB_SEEKER);
//        }
//
//        if (companyRepository.existsByEmail(jobSeeker.getEmail())) {
//            throw new RepeatedEmailErrorResponse(COMPANY);
//        }
//
//        String hashed = aesEncryptionDecryption.encrypt(jobSeeker.getPassword(), secretKey);
//
//        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
//        for (JobSeeker existingJobSeekers : jobSeekers) {
//            if (aesEncryptionDecryption.decrypt(existingJobSeekers.getPassword(), secretKey).equals(jobSeeker.getPassword())) {
//                throw new RepeatedPasswordErrorResponse(JOB_SEEKER);
//            }
//        }
//
//        List<Company> companies = companyRepository.findAll();
//        for (Company existingCompanies : companies) {
//            if (aesEncryptionDecryption.decrypt(existingCompanies.getPassword(), secretKey).equals(jobSeeker.getPassword())) {
//                throw new RepeatedPasswordErrorResponse(COMPANY);
//            }
//        }
//        jobSeeker.setPassword(hashed);
//        return jobSeekerRepository.save(jobSeeker);
//    }
//
////    @Override
////    public JobSeeker login(JobSeeker jobSeeker) {
////        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
////        for (JobSeeker existingJobSeekers : jobSeekers) {
////            if (!jobSeeker.getEmail().equals(existingJobSeekers.getEmail())) {
////                throw new IncorrectEmailErrorResponse();
////            } else if (!aesEncryptionDecryption.decrypt(existingJobSeekers.getPassword(), secretKey)
////                    .equals(jobSeeker.getPassword()) && jobSeeker.getEmail().equals(existingJobSeekers.getEmail())) {
////                throw new IncorrectPasswordErrorResponse();
////            } else {
////                return jobSeeker;
////            }
////        }
////        return null;
////    }
//
//    @Override
//    public JobSeeker deleteByID(UUID id) throws NotExistingErrorResponse {
//        JobSeeker jobSeekerToBeRemoved = jobSeekerRepository.findById(id)
//                .orElseThrow(() -> new NotExistingErrorResponse("Employee not exist with id: " + id));
//        if (jobSeekerRepository.existsById(jobSeekerToBeRemoved.getId())) {
//            jobSeekerRepository.deleteById(id);
//            return jobSeekerToBeRemoved;
//        } else {
//            throw new NotExistingErrorResponse(JOB_SEEKER);
//        }
//    }
//
//    @Override
//    public JobSeeker updateByID(UUID id, JobSeeker updatedJobSeeker) throws NotExistingErrorResponse {
//        if (jobSeekerRepository.existsById(updatedJobSeeker.getId())) {
//            return jobSeekerRepository.findById(id)
//                    .map(account -> {
//                        account.setEmail(updatedJobSeeker.getEmail());
//                        account.setPassword(updatedJobSeeker.getPassword());
//                        return jobSeekerRepository.save(account);
//                    })
//                    .orElseThrow(() -> new RuntimeException("JobSeeker not found with id " + id));
//        } else {
//            throw new NotExistingErrorResponse(JOB_SEEKER);
//        }
//    }
//
//    @Override
//    public JobSeeker getByID(UUID id) throws NotExistingErrorResponse {
//        JobSeeker jobSeekerToBeReturned = jobSeekerRepository.findById(id)
//                .orElseThrow(() -> new NotExistingErrorResponse("Employee not exist with id: " + id));
//        if (!jobSeekerRepository.existsById(jobSeekerToBeReturned.getId())) {
//            throw new NotExistingErrorResponse(JOB_SEEKER);
//        }
//        jobSeekerRepository.getReferenceById(id);
//        return jobSeekerToBeReturned;
//    }
//}
