package com.yapp.core.persistence.oauth.entity;

import com.yapp.core.constant.OAuthProvider;
import com.yapp.core.persistence.user.entity.User;
import com.yapp.core.persistence.util.EntityParameterUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "oauth_info")
public class OAuthInfo {
    public static final OAuthInfo INVALID = new INVALID();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private OAuthProvider provider;
    private String oauthId;

    @Setter
    @ManyToOne(fetch = EAGER)
    private User user;

    private OAuthInfo(OAuthProvider provider, String oauthId) {
        this.provider = provider;
        this.oauthId = oauthId;
    }

    public static OAuthInfo kakao(String providerKind, String oauthId) {
        EntityParameterUtils.assertAble(oauthId);

        return new OAuthInfo(OAuthProvider.KAKAO, oauthId);
    }

    public static OAuthInfo apple(String providerKind, String oauthId) {
        EntityParameterUtils.assertAble(oauthId);

        return new OAuthInfo(OAuthProvider.APPLE, oauthId);
    }

    private static class INVALID extends OAuthInfo {
        @Override
        public OAuthProvider getProvider() {
            return OAuthProvider.NULL;
        }
    }
}
