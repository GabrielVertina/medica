package com.example.medica.service;
import com.example.medica.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.medica.dto.TokenDTO;
import com.example.medica.dto.UserDtoRegister;
import com.example.medica.entity.User;

@Service
public class UserServiceRegister {
    
    private final UserRepository userRepository;
@SuppressWarnings("unused")
private final PasswordEncoder passwordEncoder;

    UserServiceRegister(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDTO userRegister(UserDtoRegister userDtoRegister, PasswordEncoder passwordEncoder, TokenDTO tokenDTO) throws Exception {
User user = new User();

    user.setName(userDtoRegister.getName());
user.setEmail(userDtoRegister.getEmail());
// se o usuario inserir um email que ja esta cadastrado no banco, ou seja ja passou pelo jpa, retorna exceção;
if(userRepository.existsByEmail(userDtoRegister.getEmail()))
    throw new Exception("Email ja cadastrado");

  user.setPassword(passwordEncoder.encode(userDtoRegister.getPassword()));  
userRepository.save(user);

return tokenDTO;

    }

  


    

}