package kr.smarket.application.Controller;

import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.Domain.BusinessMember;
import kr.smarket.application.Domain.Enum.UserType;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Repository.BusinessMemberRepository;
import kr.smarket.application.Repository.MemberRepository;
import kr.smarket.application.Service.MemberService;
import kr.smarket.application.Service.ProductService;
import kr.smarket.application.Service.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductController {

    private final S3UploadService s3UploadService;
    private final ProductService productService;

    private final MemberRepository memberRepository;
    private final BusinessMemberRepository businessMemberRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message","Hello, Spring Security");
        } else {
            model.addAttribute("message","WelCome DashBoard, " + principal.getName());
        }
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message","Hello, Spring Security");
        } else {
            model.addAttribute("message","WelCome Admin, " + principal.getName());
        }
        return "admin";
    }

    @PostMapping("/register")
    public String upload(
            @RequestParam("image") MultipartFile file,
            @ModelAttribute(name = "request") RegisterProductRequest request,
            Principal principal,
            Authentication authentication
    ) throws IOException {
        String src = null;
        if(!file.isEmpty()) {
            src = s3UploadService.upload(file, "smarket");
        }
        productService.registerProduct(request,getMember(authentication),src);
        return "index";
    }

    public BusinessMember getMember(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberRepository.findByUserId(userDetails.getUsername());
        if (member.getUserType()== UserType.CLIENT) {
            return (BusinessMember)member;
        } else {
            return businessMemberRepository.findByUserId(userDetails.getUsername());
        }
    }


}
