package com.example.jobs.Service;

import com.example.jobs.Model.User;
import com.example.jobs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(Integer userId, User user) {

        User olduser = userRepository.findById(userId).orElse(null);

        if (olduser != null) {
            olduser.setName(user.getName());
            olduser.setEmail(user.getEmail());
            olduser.setPassword(user.getPassword());
            olduser.setRole(user.getRole());
            userRepository.save(olduser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Integer userId) {

        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getId().equals(userId)) {
                userRepository.delete(user);
                return true;
            }
        }
        return false;
    }
}
