package com.yapp.api.domain.file.service;

import static java.util.UUID.*;

import org.springframework.stereotype.Component;

@Component
public class KeyGenerator {
	public String generate() {
		return randomUUID().toString();
	}
}
