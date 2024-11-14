package com.erbaproger.water_balance_tracker.service;

import com.erbaproger.water_balance_tracker.entities.User;
import com.erbaproger.water_balance_tracker.exceptions.ResourceNotFoundException;
import com.erbaproger.water_balance_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userUpdated) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userUpdated.getName());
                    user.setDailyWaterGoal(userUpdated.getDailyWaterGoal());
                    user.setEmail(userUpdated.getEmail());
                    user.setNotificationsEnabled(userUpdated.isNotificationsEnabled());
                    user.setNotificationFrequency(userUpdated.getNotificationFrequency());
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
