package kr.smarket.application.Domain;

import kr.smarket.application.Domain.Common.LogDateTime;
import kr.smarket.application.Domain.Enum.Region;
import kr.smarket.application.Domain.Enum.Role;
import kr.smarket.application.Domain.Enum.UserType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends LogDateTime {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable=false, unique=true, length=20)
    private String userName;

    @Column(nullable=false, unique=true, length=20)
    private String userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(nullable=false, length=50)
    private String email;

    private String address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private Role role;
}
