package kr.smarket.application.Controller;

import kr.smarket.application.DTO.SignUpBusinessRequest;
import kr.smarket.application.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup-business")
    public String signUp(
            @ModelAttribute(name = "request") SignUpBusinessRequest request,
            Model model,
            Principal principal
    ) {
        memberService.createMember(request);
        return "index";
    }
}
