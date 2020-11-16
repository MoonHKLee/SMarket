package kr.smarket.application.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    String userId;

    @NotEmpty
    String password;
}
