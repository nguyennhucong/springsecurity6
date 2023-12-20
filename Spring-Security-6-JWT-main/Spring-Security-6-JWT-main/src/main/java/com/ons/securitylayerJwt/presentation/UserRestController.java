package com.ons.securitylayerJwt.presentation;


import com.ons.securitylayerJwt.businessLogic.IUserService;
import com.ons.securitylayerJwt.dto.LoginDto;
import com.ons.securitylayerJwt.dto.RegisterDto;
import com.ons.securitylayerJwt.models.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {


    private final IUserService iUserService ;

    //RessourceEndPoint:http://localhost:8087/api/user/register
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto) {
        return  iUserService.register(registerDto);
    }

    //RessourceEndPoint:http://localhost:8087/api/user/authenticate
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto)
    { return  iUserService.authenticate(loginDto);}

    //RessourceEndPoint:http://localhost:8087/api/user/hi
    @GetMapping("/hi")
    public String Hello(){
        return "Đây là trang user";
    }

    @GetMapping("/register")
    public String register(Model model){
        RegisterDto rd = new RegisterDto();
        model.addAttribute("registerDto", rd);
        return "register";
    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

//    @PostMapping("/process")
//    public String process(@Validated @ModelAttribute("registerDto") RegisterDto registerDto,
//                          BindingResult result,
//                          Model model,
//                          HttpSession session
//    ){
//
//        iUserService.register(registerDto);
//        session.setAttribute("myuser", registerDto);
//        return "confirmation";
//    }

    @PostMapping("/process")
    public String process(@Validated @ModelAttribute("registerDto") RegisterDto registerDto,
                          BindingResult result,
                          Model model,
                          HttpSession session) {

        ResponseEntity<?> responseEntity = iUserService.register(registerDto);

        if (responseEntity.getStatusCode() == HttpStatus.SEE_OTHER) {
            model.addAttribute("my_error", "Email đã được sử dụng!");
            return "register"; // Trả về trang đăng ký với thông báo lỗi
        }

        // Xử lý khi đăng ký thành công
        session.setAttribute("myuser", registerDto);
        return "confirmation"; // Trả về trang xác nhận đăng ký
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "login";
    }

}
