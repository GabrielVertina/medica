package com.example.medica.dto;

public class OtpRequestDto {
        private String email;
    private String otpCode;
    public OtpRequestDto(){
    }
    public OtpRequestDto(String email, String otpCode){
        this.email = email;
        this.otpCode = otpCode;
    }
    public String getOtpCode(){

        return otpCode;
    }
        public String getEmail() {
            return email;
        }
    public void setEmail(String email) {
        this.email = email;
    }
public void setOtpCode(String otpCode){
        this.otpCode =otpCode;
}


}