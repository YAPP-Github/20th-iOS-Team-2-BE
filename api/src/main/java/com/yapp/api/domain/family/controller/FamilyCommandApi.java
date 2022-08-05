package com.yapp.api.domain.family.controller;

import static com.yapp.core.constant.ApiConstant.*;
import static org.springframework.http.MediaType.*;

import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapp.api.domain.family.controller.dto.FamilyRequest;
import com.yapp.api.domain.family.controller.dto.FamilyResponse;
import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.api.domain.home.HomeRequest;
import com.yapp.api.domain.home.HomeService;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.api.global.security.auth.resolver.MustAuthenticated;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.core.error.exception.ErrorCode;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.util.KafkaMessageTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FamilyCommandApi {
	@Value("${kafka.topic-name}")
	private String TOPIC;

	private final FamilyService familyService;
	private final HomeService homeService;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	// Sync
	@PostMapping(value = _FAMILY, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	ResponseEntity<FamilyResponse.Create> createFamily(@MustAuthenticated User user,
													   @Valid @RequestBody FamilyRequest.Create request) {
		Family createdFamily = familyService.create(user, request.getFamilyName(), request.getFamilyMotto());
		return ResponseEntity.ok(new FamilyResponse.Create(createdFamily.getId()));
	}

	// Async
	@PatchMapping(value = _FAMILY, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modifyFamilyInfo(@MustAuthenticated User user, @RequestBody FamilyRequest.Modify request) {
		CompletableFuture.runAsync(() -> familyService.modify(user,
															  request.getImageLink(),
															  request.getFamilyName(),
															  request.getFamilyMotto(),
															  request.getNicknames()))
						 .exceptionally(throwable -> {
							 log.error("[ERROR] {}", throwable.getMessage());
							 return null;
						 });
		return ResponseEntity.ok()
							 .build();
	}

	@DeleteMapping(value = _FAMILY_RESOURCE)
	ResponseEntity<Void> removeFamily(@PathVariable(value = FAMILY_ID) Long familyId) {
		return ResponseEntity.noContent()
							 .build();
	}

	// Kafka
	@PostMapping(value = _FAMILY_GREETING_MESSAGE, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createGreetingWithMessage(@AuthenticationHasFamily User user,
												   @RequestBody HomeRequest.Greeting request) throws
																							  JsonProcessingException {
		homeService.greet(user, request.getContent());

		kafkaSending(user, request.getContent());

		return ResponseEntity.ok()
							 .build();
	}

	// Kafka
	@PostMapping(value = _FAMILY_GREETING_EMOJI, consumes = APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createGreetingWithEmoji(@AuthenticationHasFamily User user,
												 @RequestBody HomeRequest.GreetWithEmoji request) throws
																								  JsonProcessingException {
		homeService.emoji(user, request.getEmojiNumber());

		kafkaSending(user, request.getEmojiNumber());

		return ResponseEntity.ok()
							 .build();
	}

	@PostMapping(value = "/family/join")
	ResponseEntity<Void> join2(@MustAuthenticated User user, @RequestParam(value = "code") String code) {
		if (user.getFamily() != null) {
			throw new BaseBusinessException(ErrorCode.ALREADY_JOINED);
		}

		familyService.join(user, code);

		return ResponseEntity.ok()
							 .build();
	}

	private <T> void kafkaSending(User user, T body) throws JsonProcessingException {
		String message = objectMapper.writeValueAsString(KafkaMessageTemplate.message(user, body));
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(new ProducerRecord<String, String>(
			TOPIC,
			message));

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

