package account.database.management.system.repository;

import account.database.management.system.model.Company;
import account.database.management.system.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobSeekerJPARepository extends JpaRepository<JobSeeker, UUID> {
//    boolean existsByEmail(String email);
    Optional<JobSeeker> findJobSeekerByLogin(String email);

}
