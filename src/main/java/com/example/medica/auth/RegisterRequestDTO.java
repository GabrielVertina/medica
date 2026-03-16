package com.example.medica.auth;

import java.util.Optional;

import com.example.medica.model.UserRoot;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class RegisterRequestDTO {

@NotBlank(message = "nome é obrigatório")
@Size(min = 3,max = 35, message = "Nome deve conter entre 3 a 35 caracteres.")
@Pattern(regexp = "^[A-Za-zÀ-ÿ]+\s+[A-Za-zÀ-ÿ]+.*$", message = "Foi inserido caracteres inválidos")
    private String name;
@NotBlank(message = "email é obrigatório")
    @Email(message = "Formato de email invalido")
    private String email;
@NotBlank(message = "Senha é obrigatória;")
@Size(min = 8, max = 64,message = "Senha deve conter pelo menos 8 caracteres.")
@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]+$", message = "Senha deve ter letras e números"
)
private String password;

    private String crm;
    public RegisterRequestDTO() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

public void setCrm(String crm){
    this.crm = crm;

}

public String getCrm(){
    return crm;
}
public boolean setEmail(Optional<UserRoot> byEmail) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
}



}












