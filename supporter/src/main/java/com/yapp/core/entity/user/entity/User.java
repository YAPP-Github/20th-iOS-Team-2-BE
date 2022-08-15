package com.yapp.core.entity.user.entity;

import com.yapp.core.entity.common.BaseEntity;
import com.yapp.core.entity.family.persistence.entity.Family;
import com.yapp.core.entity.oauth.entity.OAuthInfo;
import com.yapp.core.entity.user.entity.element.Authority;
import com.yapp.core.entity.user.entity.element.OAuthInfos;
import com.yapp.core.entity.user.entity.element.ProfileInfo;
import com.yapp.core.entity.util.EntityParameterUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthday;

    @Enumerated(STRING)
    private Authority authority;

    @Embedded
    private ProfileInfo profileInfo;

    @Embedded
    private OAuthInfos oAuthInfos;

    @ManyToOne(fetch = EAGER)
    private Family family;

    @Builder
    private User(String name, LocalDate birthday, ProfileInfo profileInfo, OAuthInfo oAuthInfo) {
        this.name = name;
        this.birthday = birthday;
        this.profileInfo = profileInfo;
        this.oAuthInfos = new OAuthInfos(Set.of(oAuthInfo));

        authority = Authority.USER;
    }

    public static User normalUser(String name, LocalDate birthday, ProfileInfo profileInfo, OAuthInfo oAuthInfo) {
        User initializedUser = User.builder()
                .name(name)
                .birthday(birthday)
                .profileInfo(profileInfo)
                .oAuthInfo(oAuthInfo)
                .build();
        oAuthInfo.setUser(initializedUser);

        return initializedUser;
    }

    public void setUp(String name, String nickname, String roleInFamily, LocalDate birthday) {
        EntityParameterUtils.assertAbles(name, nickname, roleInFamily);
        this.name = name;

        EntityParameterUtils.assertNull(birthday);
        this.birthday = birthday;

        this.profileInfo = ProfileInfo.builder()
                .firstNickname(nickname)
                .roleInFamily(roleInFamily)
                .build();
    }

    // nullable params
    public void modify(String nickname, String imageLink, LocalDate birthDay, String roleInFamily) {
        patchBirthDay(birthDay);
        profileInfo.patch(nickname, imageLink, roleInFamily);
    }

    private void patchBirthDay(LocalDate birthDay) {
        if (birthDay != null && !this.birthday.isEqual(birthDay)) {
            this.birthday = birthDay;
            // 생일 바뀌었으니, 캘린더의 생일정보에도 수정이 필요하다.

        }
    }

    public String getNicknameForUser(User user) {
        return this.profileInfo.getNicknameFromOther(user.id);
    }

    public String getOriNickname() {
        return profileInfo.originalNickname();
    }

    public String getRoleInFamily() {
        return profileInfo.getRoleInFamily();
    }

    public String imageLink() {
        return profileInfo.getImageLink();
    }

    public LocalDateTime getContentLastModified() {
        return profileInfo.getModifiedDate();
    }

    public int getEmoji() {
        return profileInfo.getEmoji();
    }

    public void setContent(String content) {
        this.profileInfo.updateContent(content);
    }

    public void setEmoji(int emoji) {
        this.profileInfo.updateEmoji(emoji);
    }

    public boolean isEmpty() {
        return "".equals(getName());
    }

    public void patchNickname(Long orderedId, String newNickname) {
        this.profileInfo.patch(orderedId, newNickname);
    }

    public static class ANONYMOUS extends User {
        @Override
        public Authority getAuthority() {
            return Authority.ANONYMOUS;
        }
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
