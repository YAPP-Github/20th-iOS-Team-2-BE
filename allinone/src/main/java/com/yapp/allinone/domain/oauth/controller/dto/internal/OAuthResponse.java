package com.yapp.allinone.domain.oauth.controller.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : daehwan2yo
 * Date : 2022/07/17
 * Info :
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthResponse {
    protected String id;
    private String provider;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FromKakao extends OAuthResponse {
        private Info kakao_account;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Info {
            private boolean profile_needs_agreement;
            private boolean profile_nickname_needs_agreement;
            private boolean profile_image_needs_agreement;
            private Profile profile;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Profile {
                private String nickname;
                private String thumbnail_image_url;
                private String profile_image_url;
                private boolean is_default_image;
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FromApple extends OAuthResponse {
        List<Key> keys = new ArrayList<>();
        private String token;

        @Getter
        @Setter
        public static class Key {
            private String kty;
            private String kid;
            private String use;
            private String alg;
            private String n;
            private String e;
        }
    }

    public String combinedInfo() {
        return provider + ":" + id;
    }

    public String getProvider() {
        return provider.toUpperCase();
    }

    public static class EMPTY extends OAuthResponse {
        @Override
        public String getId() {
            return "";
        }

        @Override
        public String getProvider() {
            return "Empty";
        }
    }
}
