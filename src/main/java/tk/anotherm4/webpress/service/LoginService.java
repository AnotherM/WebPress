package tk.anotherm4.webpress.service;

import tk.anotherm4.webpress.domain.Users;
import tk.anotherm4.webpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findByUser(String user) {
        return userRepository.findByUser(user);
    }

//    public Users findUserByUser(String user) {
//        return userRepository.findUsersByUser(user);
//    }
}
