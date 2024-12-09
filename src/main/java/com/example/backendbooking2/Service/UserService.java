package com.example.backendbooking2.Service;

import com.example.backendbooking2.Entity.Customer;
import com.example.backendbooking2.Entity.User;
import com.example.backendbooking2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getAUserByID(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> getAllUsers(Long id){
        return userRepository.findById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(Long id, User updateUser){
        User exsistingUser = userRepository.findById(id)
                .orElseThrow( () -> new RuntimeException(""));

        exsistingUser.setName(updateUser.getName());
        exsistingUser.setEmail(updateUser.getEmail());
        exsistingUser.setPassword(updateUser.getPassword());
        exsistingUser.setRole(updateUser.getRole());
        return userRepository.save(exsistingUser);
    }
    public void deleteUser(Long ID){
        userRepository.deleteById(ID);
    }



}
