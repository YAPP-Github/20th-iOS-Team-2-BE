package com.yapp.api.domain.home;

import com.yapp.api.domain.home.model.HomeResponse;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity home(@AuthenticationHasFamily User user) {
        return ResponseEntity.ok(new HomeResponse(user.getFamily().getId()));
    }
}
