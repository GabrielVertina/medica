package com.example.medica.service;
import com.example.medica.dto.UserDtoLogin;
import com.example.medica.entity.User;
import com.example.medica.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceLogin {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserServiceLogin(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    public String userLogin
            (UserDtoLogin userDtoLogin) throws Exception {
        User user = userRepository.findByEmail(userDtoLogin.getEmail()
                )
                .orElseThrow(
                        () -> new RuntimeException("Usuário não encontrado."));
        if (!user.getVerified()) {
            throw new RuntimeException("Verifique seu email antes de fazer login");
        }
        if (!passwordEncoder.matches(userDtoLogin.getPassword(), user.getPassword()
        )) {
            throw new RuntimeException("Senha incorreta");
        }
        return userDtoLogin.getEmail();
    }
}