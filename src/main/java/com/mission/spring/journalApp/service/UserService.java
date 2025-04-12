package com.mission.spring.journalApp.service;

import com.mission.spring.journalApp.entity.User;
import com.mission.spring.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void deleteUserById(ObjectId id) {
        userRepository.deleteById(id);
    }
}
