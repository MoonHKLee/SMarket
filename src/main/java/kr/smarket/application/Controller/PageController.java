package kr.smarket.application.Controller;

import kr.smarket.application.DTO.Request.LoginRequest;
import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.DTO.Request.SignUpBusinessRequest;
import kr.smarket.application.DTO.Request.SignUpRequest;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final ProductService productService;

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
