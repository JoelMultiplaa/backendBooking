package com.example.backendbooking2.Service;

import com.example.backendbooking2.DTO.LoginDto;
import com.example.backendbooking2.Entity.Role;
import com.example.backendbooking2.Entity.User;
import com.example.backendbooking2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserRepository userData;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean authCredentials(LoginDto loginDto){
       String email = loginDto.getEmail();
       String name = loginDto.getName();
       String rawPassword= loginDto.getPassword();
        Optional<User> adminEmailorName= userData.findByNameOrEmail(name, email);
        if (adminEmailorName.isEmpty())
        {
            return false;
        }
        User admin = adminEmailorName.get();
       return passwordEncoder.matches(rawPassword,admin.getPassword());
    }
    public Optional<User> signUpUser(LoginDto dto)
    {
        Optional<User> availableUser = userData.findByNameOrEmail(dto.getName(), dto.getEmail());
        if (availableUser.isPresent())
        {
            return Optional.empty();
        }
        User signUpUser = new User();

        signUpUser.setName(dto.getName());
        signUpUser.setEmail(dto.getEmail());
        signUpUser.setPassword(passwordEncoder.encode(dto.getPassword())); //Den her encoder password før den rammer databasen.
        signUpUser.setRole(dto.getRole() != null ? dto.getRole() : Role.EMPLOYEE); // Det her er en tanari der checker for role om den eksistere og valgte role.

        User savedUser = userData.save(signUpUser);


        return Optional.of(savedUser);
    }
    public Optional<User> updateUser(LoginDto dto, Long id){
        Optional<User> previousUser = userData.findByNameOrEmail(dto.getEmail(), dto.getName());
        if (previousUser.isEmpty()){
            return Optional.empty();
        }

        User updatedUser = previousUser.get();
        if (dto.getName() != null && !dto.getName().isBlank()) {
            updatedUser.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            updatedUser.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            updatedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRole() != null) {
            updatedUser.setRole(dto.getRole());
        }
        User user =userData.save(updatedUser);
        return Optional.of(user);
    }
    public void deleteAccount(Long id)
    {
        Optional<User> verifyUser = userData.findById(id);
        if (verifyUser.isPresent()) userData.deleteById(id);
    }
    public User getUserOrEmail(String name, String email){
        return userData.findByNameOrEmail(name, email).orElseThrow(()->new RuntimeException("User Not Found!!!"));
    }
}
