package kr.smarket.application.Controller;

import kr.smarket.application.DTO.LoginRequest;
import kr.smarket.application.DTO.SignUpBusinessRequest;
import kr.smarket.application.DTO.SignUpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class PageController {

    @GetMapping("/")
    public String getIndexPage(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message","Hello, Spring Security");
        } else {
            model.addAttribute("message","WelCome back, " + principal.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute(name = "request") LoginRequest request) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        return "register";
    }

    @GetMapping("/signup-business")
    public String getSignupBusinessPage(@ModelAttribute(name = "request") SignUpBusinessRequest request, Model model) {
        return "registerBusiness";
    }

    @GetMapping("/signup-client")
    public String getSignupClientPage(@ModelAttribute(name = "request") SignUpRequest request, Model model) {
        return "registerClient";
    }

    @GetMapping("/opinion")
    public String getOpinionPage(Model model) {
        return "opinion";
    }
    @GetMapping("/search")
    public String getSearchPage(Model model) {
        return "search";
    }
}
