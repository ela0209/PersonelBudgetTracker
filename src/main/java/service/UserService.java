package service;


import exception.UserAlreadyExistsException;
import exception.ResourceNotFoundException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Bu kullanıcı adı zaten kullanılıyor: " + username);
        }
        User user = new User(username, password);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı. ID: " + id));
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password));
    }
}
