package com.yapp.realtime.entity.user.entity.element;

import com.yapp.realtime.constant.OAuthProvider;
import com.yapp.realtime.entity.oauth.entity.OAuthInfo;
import com.yapp.realtime.error.exception.ErrorCode;
import com.yapp.realtime.error.exception.PersistenceException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PUBLIC)
public class OAuthInfos {
    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = ALL, orphanRemoval = true)
    private Set<OAuthInfo> oAuthInfos = new HashSet<>();

    public boolean contains(OAuthInfo oAuthInfo) {
        return oAuthInfos.contains(oAuthInfo);
    }

    public OAuthInfo getOAuthInfo(OAuthProvider provider) {
        return oAuthInfos.stream()
                .filter(info -> info.getProvider() == provider)
                .findFirst()
                .orElseThrow(() -> new PersistenceException(ErrorCode.OAUTH_ERROR));
    }
}
