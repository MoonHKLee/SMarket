package kr.smarket.application.Controller;

import kr.smarket.application.DTO.Request.*;
import kr.smarket.application.DTO.Response.OpinionResponse;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Enum.UserType;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Service.MemberService;
import kr.smarket.application.Service.OpinionService;
import kr.smarket.application.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final ProductService productService;
    private final MemberService memberService;
    private final OpinionService opinionService;

    @GetMapping("/")
    public String getIndexPage(Model model, Authentication authentication) {
        if(authentication==null) {
            return "index";
        }
        model.addAttribute("userType",memberService.getMember((UserDetails) authentication.getPrincipal()).getUserType());
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute(name = "request") LoginRequest request) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute(name = "request") RegisterProductRequest request, Model model) {
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
        model.addAttribute("opinions",opinionService.getAllOpinions());
        return "opinion";
    }

    @GetMapping("/search")
    public String getSearchPage(
            @ModelAttribute(name = "data") ProductResponse response,
            @RequestParam(value = "marketName", defaultValue = "") String marketName,
            @RequestParam(value = "productName", defaultValue = "") String productName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model
    ) {
        Page<Product> productPage = productService.getProductPage(marketName,productName,page);
        model.addAttribute("marketName",marketName);
        model.addAttribute("productName",productName);
        model.addAttribute("startPage",(page) - (page % 10));
        model.addAttribute("nowPage",page);
        model.addAttribute("maxPage",10);
        model.addAttribute("totalPages",productPage.getTotalPages());
        model.addAttribute("list",productService.getAllProductList(productPage));
        return "search";
    }
}
