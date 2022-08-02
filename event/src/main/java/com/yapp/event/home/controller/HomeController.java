package com.yapp.event.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.core.persistance.user.entity.User;
import com.yapp.api.global.security.auth.resolver.AuthenticationHasFamily;
import com.yapp.event.home.response.HomeResponse;
import com.yapp.event.home.service.HomeService;

import lombok.RequiredArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
@RestController("/family")
@RequiredArgsConstructor
public class HomeController {
	private final HomeService homeService;

	@GetMapping("/home")
	public ResponseEntity<HomeResponse.Info> homeInfo(@AuthenticationHasFamily User user) {
		return ResponseEntity.ok(homeService.info(user));
	}

}