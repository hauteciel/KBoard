package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public void login(Model model){}


    @PostMapping("/login")
    public void loginProcess(){
        System.out.println("/user/login [POST] 가 호출되나?");   // .loginProcessingUrl() 가 설정되어 있으면 호출안된다.
    }

    // onAuthenticationFailure 에서 로그인 실패시 forwarding 용
    // request 에 담겨진 attribute 는 Thymeleaf 에서 그대로 표현 가능.
    @PostMapping("/loginError")
    public String loginError(){
        return "user/login";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth(){
        return "common/rejectAuth";
    }

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String registerOk(@Valid User user,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes){

        // 검증 에러가 있었다면 redirect 한다.
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("username", user.getUsername());
            redirectAttributes.addFlashAttribute("name", user.getName());
            redirectAttributes.addFlashAttribute("email", user.getEmail());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                // 가장 처음에 발견된 에러만 보내기
                redirectAttributes.addFlashAttribute("error", err.getCode());
                break;
            }

            return "redirect:/user/register";
        }


        // 검증 에러 없었으면 회원 등록 진행
        int cnt = userService.register(user);
        model.addAttribute("result", cnt);
        return "/user/registerOk";
    }

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void intiBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }


}







