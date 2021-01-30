package com.softserve.itacademy.web_security;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AccesDeniedController {


    @Controller
    public class AccessDeniedController {
        @GetMapping(path = "/forbidden")
        public String accessPage() {
            return "access-denied";
        }
    }
}
