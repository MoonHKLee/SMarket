package kr.smarket.application.Domain;

import kr.smarket.application.Domain.Common.LogDateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member extends LogDateTime {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable=false, unique=true, length=20)
    private String username;

    @Column(nullable=false, unique=true, length=20)
    private String nickname;

    private String password;

    private Region region;

    @Column(nullable=false, length=50)
    private String email;

    private String address;

    private String userType;

    private String role;
}
