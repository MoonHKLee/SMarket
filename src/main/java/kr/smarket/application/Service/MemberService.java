package kr.smarket.application.Service;

import kr.smarket.application.DTO.SignUpBusinessRequest;
import kr.smarket.application.Domain.Enum.Role;
import kr.smarket.application.Domain.Enum.UserType;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Enum.Region;
import kr.smarket.application.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(SignUpBusinessRequest request) {
        return memberRepository.save(
                Member.builder()
                        .userName(request.getUserName())
                        .userId(request.getUserId())
                        .address(request.getAddress())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .phoneNumber(request.getPhoneNumber())
                        .region(regionToEnum(request.getRegion()))
                        .role(Role.USER)
                        .userType(UserType.BUSINESS)
                        .build()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) {
            throw new UsernameNotFoundException(userId);
        }

        return User.builder()
                .username(member.getUserId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    private Region regionToEnum(String string) {
        switch (string) {
            case "SEOUL":
                return Region.SEOUL;
            case "INCHEON":
                return Region.INCHEON;
            case "DAEJEON":
                return Region.DAEJEON;
            case "DAEGU":
                return Region.DAEGU;
            case "GWANGJU":
                return Region.GWANGJU;
            case "BOOSAN":
                return Region.BOOSAN;
            case "ULSAN":
                return Region.ULSAN;
            case "GYEONGGI":
                return Region.GYEONGGI;
            case "GANGWON":
                return Region.GANGWON;
            case "CHUNGBUK":
                return Region.CHUNGBUK;
            case "CHUNGNAM":
                return Region.CHUNGNAM;
            case "GYEONGBUK":
                return Region.GYEONGBUK;
            case "GYEONGNAM":
                return Region.GYEONGNAM;
            case "JEONBUK":
                return Region.JEONBUK;
            case "JEONNAM":
                return Region.JEONNAM;
            case "JEJU":
                return Region.JEJU;
            case "SEJONG":
                return Region.SEJONG;
            default:
                return Region.NONE;
        }
    }
}
