package kr.smarket.application.Service;

import kr.smarket.application.DTO.SignUpBusinessRequest;
import kr.smarket.application.DTO.SignUpRequest;
import kr.smarket.application.Domain.BusinessMember;
import kr.smarket.application.Domain.Enum.Category;
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
    public Member createMemberBusiness(SignUpBusinessRequest request) {
        BusinessMember member = new BusinessMember();
        member.setCategory(categoryToEnum(request.getCategory()));
        member.setMarketName(request.getMarketName());
        member.setStoreName(request.getStoreName());
        member.setOfficeNumber(request.getOfficeNumber());
        member.setUserName(request.getUserName());
        member.setUserId(request.getUserId());
        member.setAddress(request.getAddress());
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setRegion(regionToEnum(request.getRegion()));
        member.setRole(Role.USER);
        member.setUserType(UserType.BUSINESS);

        return memberRepository.save(member);
    }

    @Transactional
    public Member createMemberClient(SignUpRequest request) {
        return memberRepository.save(Member.builder()
                .userName(request.getUserName())
                .userId(request.getUserId())
                .password(request.getPassword())
                .region(regionToEnum(request.getRegion()))
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .userType(UserType.CLIENT)
                .role(Role.USER)
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

    private Category categoryToEnum(String string) {
        switch (string) {
            case "NONG_SAN_MUL":
                return Category.NONG_SAN_MUL;
            case "CHEONG_GWA_MUL":
                return Category.CHEONG_GWA_MUL;
            case "JAP_HWA_JEOM":
                return Category.JAP_HWA_JEOM;
            case "JEONG_YUK_JEOM":
                return Category.JEONG_YUK_JEOM;
            case "SU_SAN_MUL":
                return Category.SU_SAN_MUL;
            default:
                return Category.NONE;
        }
    }
}
