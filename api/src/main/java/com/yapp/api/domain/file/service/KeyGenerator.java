package com.yapp.api.domain.file.service;

import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Component
public class KeyGenerator {
    public String generate() {
        return randomUUID().toString();
    }
}
