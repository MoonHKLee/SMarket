package kr.smarket.application.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        return "register";
    }

    @GetMapping("/signup-business")
    public String getSignupBusinessPage(Model model) {
        return "registerBusiness";
    }

    @GetMapping("/signup-client")
    public String getSignupClientPage(Model model) {
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
