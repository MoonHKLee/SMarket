package kr.smarket.application.DTO;

import lombok.Data;

public class SignUpBusinessRequest extends SignUpRequest {
    String marketName;
    String officeName;
    String category;
    String officeNumber;
}
