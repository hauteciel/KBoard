package com.lec.spring.domain;

import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService; // 회원가입시 입력한 username 이 이미 가입한 username 인지 확인하려면 DB 접근 필요

    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");
        boolean result = User.class.isAssignableFrom(clazz);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;

        String username = user.getUsername();
        if(username == null || username.trim().isEmpty()){
            errors.rejectValue("username", "username 은 필수입니다");
        } else if(userService.isExist(username)){
            // 이미 등록된 중복 아이디(username) 으로 가입하려 하면
            errors.rejectValue("username", "이미 존재하는 아이디(username) 입니다");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "name 은 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "password 는 필수입니다");

        // email
        // 입력이 되어 있으면 정규표현식 패턴 체크
        // TODO

        // 입력 password, re_password 가 동일한지 비교
        if(!user.getPassword().equals(user.getRe_password())){
            errors.rejectValue("re_password", "비밀번호와 비밀번호 확인 입력값은 같아야 합니다");
        }

    }
}











