package com.yapp.supporter.constant;

/**
 * Author : daehwan2yo
 * Date : 2022/08/09
 * Info :
 **/
public enum OAuthProvider {
    KAKAO("kakao"), APPLE("apple"), NULL("");

    OAuthProvider(String value) {
        this.value = value.toUpperCase();
    }

    public static boolean isKakao(String providerKind) {
        return KAKAO.value.equalsIgnoreCase(providerKind);
    }

    public static boolean isApple(String providerKind) {
        return APPLE.value.equalsIgnoreCase(providerKind);
    }

    private final String value;

    public String getValue() {
        return value;
    }
}