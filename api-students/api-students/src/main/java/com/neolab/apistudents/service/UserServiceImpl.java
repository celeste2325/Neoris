package com.neolab.apistudents.service;

import com.neolab.apistudents.entity.User;
import com.neolab.apistudents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        if (!this.userRepository.existsByEmail(user.getEmail())) {
            return this.userRepository.save(user);
        }
        return null;
    }
}
