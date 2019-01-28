package tk.anotherm4.webpress.repository;

import tk.anotherm4.webpress.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 主页和管理页dao
 */
@Repository
public interface PostRepository extends JpaRepository<Posts, Integer> {
    Posts findByPid(Long pid);

    @Modifying
    @Transactional
    void deleteByPid(Long pid);
}
