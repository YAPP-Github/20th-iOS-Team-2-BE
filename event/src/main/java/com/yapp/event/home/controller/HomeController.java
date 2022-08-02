package com.yapp.event.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yapp.core.persistance.user.entity.User;
import com.yapp.core.util.resolver.AuthenticationHasFamily;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Author : daehwan2yo
 * Date : 2022/08/03
 * Info : 
 **/
@RestController("/family")
public class HomeController {

	@GetMapping("/home")
	public ResponseEntity<Response.Info> homeInfo(@AuthenticationHasFamily User user) {

	}


	class Response {

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		public class Info {

		}
	}
}