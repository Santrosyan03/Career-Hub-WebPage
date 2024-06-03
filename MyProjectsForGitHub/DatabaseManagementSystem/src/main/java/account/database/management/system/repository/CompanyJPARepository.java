package account.database.management.system.repository;

import account.database.management.system.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyJPARepository extends JpaRepository<Company, UUID> {
//    boolean existsByEmail(String email);
    Optional<Company> findCompanyByLogin(String email);
}
