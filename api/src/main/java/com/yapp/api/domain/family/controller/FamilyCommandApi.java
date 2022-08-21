package com.yapp.api.domain.family.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.yapp.supporter.util.KafkaMessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.yapp.supporter.constant.ApiConstant.FAMILY_ID;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FamilyCommandApi implements ExceptionThrowableLayer {
    @Value("${kafka.topic-name}")
    private String TOPIC;

    private final FamilyService familyService;
    private final KafkaTemplate<String, String> kafkaTemplate;
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

    private <T> void kafkaSending(User user, T body) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(KafkaMessageTemplate.message(user, body));
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(new ProducerRecord<String, String>(TOPIC, message));

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}

