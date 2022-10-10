package com.yapp.allinone.domain.family.controller;

import com.yapp.allinone.common.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.allinone.domain.family.controller.model.FamilyResponse;
import com.yapp.allinone.domain.family.service.FamilyService;
import com.yapp.allinone.domain.home.model.HomeResponse;
import com.yapp.supporter.entity.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class FamilyQueryApi {
    private final FamilyService familyService;

    @Deprecated
    @GetMapping(value = "/family/notifications", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<HomeResponse.Notifications> retrieveNotifications() {
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping(value = "/family")
    ResponseEntity<FamilyResponse.Info> retrieveInfo(@AuthenticationHasFamily User user) {
        return ResponseEntity.ok(familyService.get(user));
    }

    @GetMapping("/family/code")
    ResponseEntity<FamilyResponse.Code> code(@AuthenticationHasFamily User user) {
        return ResponseEntity.ok(FamilyResponse.Code.from(user.getFamily()
                .getCode()));
    }
}
