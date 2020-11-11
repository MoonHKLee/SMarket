package kr.smarket.application.DTO;

import lombok.Data;

@Data
public class SignUpRequest {
    String userName;
    String userId;
    String password;
    String region;
    String address;
    String email;
    String phoneNumber;
}
