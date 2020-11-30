package kr.smarket.application.Controller;

import kr.smarket.application.DTO.Request.RegisterOpinionRequest;
import kr.smarket.application.DTO.Response.OpinionResponse;
import kr.smarket.application.Service.MemberService;
import kr.smarket.application.Service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OpinionController {

    private final OpinionService opinionService;

    @GetMapping("/opinion/{id}")
    @ResponseBody
    public OpinionResponse getPost(@PathVariable Long id) {
        return opinionService.getOpinion(id);
    }

    @GetMapping("/opinions")
    @ResponseBody
    public List<OpinionResponse> getSearchedOpinions(
            @RequestParam(value = "content", defaultValue = "") String content
    ) {
        return opinionService.getSearchedOpinions(content);
    }

    @PostMapping("/opinion")
    public String createOpinion(
            @ModelAttribute(name = "request") RegisterOpinionRequest request,
            Authentication authentication,
            Model model
    ) {
        opinionService.createOpinion(request.getContent(), (UserDetails) authentication.getPrincipal());
        model.addAttribute("opinions",opinionService.getAllOpinions());
        return "opinion";
    }
}
