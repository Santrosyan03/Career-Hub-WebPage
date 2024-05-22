package account.database.management.system.repository;

import account.database.management.system.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.password = :password")
    boolean existsByPassword(String password);

    @Query("SELECT COUNT(a) > 0 FROM Account a WHERE a.email = :email")
    boolean existsByEmail(String email);
}
