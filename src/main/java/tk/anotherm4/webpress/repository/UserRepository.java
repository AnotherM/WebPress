package tk.anotherm4.webpress.repository;

import tk.anotherm4.webpress.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户dao
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUid(Long uid);

    @Modifying
    @Transactional
    void deleteByUid(Long uid);

    Users findByUser(String user);

//    Users findUsersByUser(String User);
}
