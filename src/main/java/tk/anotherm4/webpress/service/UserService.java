package tk.anotherm4.webpress.service;

import tk.anotherm4.webpress.repository.UserRepository;
import tk.anotherm4.webpress.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户的增删改查
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Page<Users> getUserPage(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }

    public List<Users> getUserList() {
        return userRepository.findAll();
    }

    public void save(Users users) {
        userRepository.save(users);
    }

    public void edit(Users users) {
        userRepository.saveAndFlush(users);
    }

    @Modifying//设置为可修改
    @Transactional//设置为可回退
    public void deleteByUid(Long uid) {
        userRepository.deleteByUid(uid);
    }

    public Users findByUid(Long uid) {
        return userRepository.findByUid(uid);
    }
}
