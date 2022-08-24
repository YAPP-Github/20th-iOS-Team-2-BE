package com.yapp.api.domain.family.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.api.domain.family.controller.model.FamilyRequest;
import com.yapp.api.domain.family.controller.model.FamilyResponse;
import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.api.global.error.exception.ApiException;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.error.exception.ErrorCode;
import com.yapp.supporter.error.exception.ExceptionThrowableLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.yapp.supporter.constant.ApiConstant.FAMILY_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FamilyCommandApi implements ExceptionThrowableLayer {
    private final FamilyService familyService;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "/family", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<FamilyResponse.Create> createFamily(
            @MustAuthenticated User user, @Valid @RequestBody FamilyRequest.Create request) {

        Family createdFamily = familyService.create(user, request.getFamilyName(), request.getFamilyMotto());

        return ResponseEntity.ok(new FamilyResponse.Create(createdFamily.getId(), createdFamily.getCode()));
    }

    @PatchMapping(value = "/family", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> modifyFamilyInfo(
            @MustAuthenticated User user, @Valid @RequestBody FamilyRequest.Modify request) {

        familyService.modify(user, request.getFamilyName(), request.getFamilyMotto(), request.getNicknames());

        return ResponseEntity.ok()
                .build();
    }

    @Deprecated
    @DeleteMapping(value = "/family/{familyId}")
    ResponseEntity<Void> removeFamily(@PathVariable(value = FAMILY_ID) Long familyId) {
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping(value = "/family/join")
    ResponseEntity<Void> join(@MustAuthenticated User user, @RequestParam(name = "code") String code) {
        if (user.getFamily() != null) {
            throw new ApiException(ErrorCode.ALREADY_JOINED, packageName(this.getClass()));
        }
        if (code.length() > 10) {
            throw new ApiException(ErrorCode.NOT_VALID_CODE_LENGTH, packageName(this.getClass()));
        }

        familyService.join(user, code);
        return ResponseEntity.ok()
                .build();
    }
}

