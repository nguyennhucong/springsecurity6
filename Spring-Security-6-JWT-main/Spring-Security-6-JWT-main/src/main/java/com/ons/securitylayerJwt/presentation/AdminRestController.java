package com.ons.securitylayerJwt.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {


    //RessourceEndPoint:http://localhost:8087/api/admin/hello
    @GetMapping("/hello")
    public String sayHello ()
    { return "Đây là trang admin";}

    @GetMapping("/hi")
    public String sayHi ()
    { return "Đây là trang admin";}

    @GetMapping("/bye")
    public String sayBye ()
    { return "Đây là trang admin";}

}
