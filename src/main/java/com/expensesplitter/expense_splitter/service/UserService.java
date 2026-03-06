package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user)  {

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

       return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
    }

    public User updateUser(Long id, User user) {

        User user1 = userRepository.findById(id).orElseThrow(() ->new RuntimeException("User Not found"));

       user1.setName(user.getName());
       user1.setEmail(user.getEmail());

       return userRepository.save(user1);
    }

    public User deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));

         userRepository.delete(user);
         return user;
    }
}
