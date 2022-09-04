package com.yapp.api.domain.home;

import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.api.domain.home.model.HomeResponse;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.supporter.entity.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final FamilyService familyService;

    public HomeController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping("/home")
    public ResponseEntity<HomeResponse.Id> home(@AuthenticationHasFamily User user) {
        return ResponseEntity.ok(new HomeResponse.Id(user.getFamily()
                .getId()));
    }

    @GetMapping("/family/home")
    ResponseEntity<HomeResponse.HomeInfo> homeInfo(@AuthenticationHasFamily User user) {

        return ResponseEntity.ok(familyService.getEvents(user));
    }
}
