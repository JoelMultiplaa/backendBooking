package com.example.backendbooking2.Service;

import com.example.backendbooking2.DTO.AuthToken;
import com.example.backendbooking2.DTO.LoginDto;
import com.example.backendbooking2.Entity.Role;
import com.example.backendbooking2.Entity.User;
import com.example.backendbooking2.Repository.UserRepository;
import com.example.backendbooking2.util.JWTutil;
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
    private final JWTutil jwTutil;

    public Optional<AuthToken> validateLogin(LoginDto dto) {
        Optional<User> userOptional = userData.findByEmail(dto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(dto.getPassword(), userOptional.get().getPassword())) {
                String token = jwTutil.generateToken(user.getEmail());
                return Optional.of(new AuthToken(token));
            }
        }
        return Optional.empty();
    }




    public Optional<User> signUpUser(LoginDto dto) {
        Optional<User> availableUser = userData.findByEmail(dto.getEmail());
        if (availableUser.isPresent()) {
            return Optional.empty(); // User already exists
        }

        User signUpUser = new User();
        signUpUser.setEmail(dto.getEmail());
        signUpUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userData.save(signUpUser);
        return Optional.of(savedUser);
    }

    public Optional<User> updateUser(LoginDto dto, Long id) {
        Optional<User> previousUserOptional = userData.findById(id);
        if (previousUserOptional.isEmpty()) {
            return Optional.empty(); // User not found
        }

        User previousUser = previousUserOptional.get();

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            previousUser.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            previousUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }


        User updatedUser = userData.save(previousUser);
        return Optional.of(updatedUser);
    }

    public void deleteAccount(Long id)
    {
        Optional<User> verifyUser = userData.findById(id);
        if (verifyUser.isPresent()) userData.deleteById(id);
    }
    public User getUserOrEmail(String name, String email) {
        return userData.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
