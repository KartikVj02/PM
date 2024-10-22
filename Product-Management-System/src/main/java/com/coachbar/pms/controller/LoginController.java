package com.coachbar.pms.controller;
import com.coachbar.pms.config.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.coachbar.pms.form.*;




@RestController
@RequestMapping("Auth")
public class LoginController {

        @Autowired
        private JWTUtil jwtUtil;

        @PostMapping("login")
        public String login(@RequestBody LoginForm form) {
            String token = jwtUtil.generateToken(form.getLoginId());
            return token;
        }

    }
