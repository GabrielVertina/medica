package com.example.medica.service;
import org.springframework.stereotype.Service;

import com.example.medica.dto.TokenDTO;
import com.example.medica.dto.UserDtoRegister;
import com.example.medica.entity.User;


@Service
public class UserServiceRegister {
    
    public TokenDTO userRegister (UserDtoRegister userDtoRegister){
User user = new User();

try {
    
user.setName(null);





} catch (Exception e) {
    // TODO: handle exception
}

    }

}
