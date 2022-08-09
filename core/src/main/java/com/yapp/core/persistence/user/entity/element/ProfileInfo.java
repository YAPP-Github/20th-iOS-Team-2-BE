package com.yapp.core.persistence.user.entity.element;

import com.yapp.core.persistence.util.EntityParameterUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.yapp.core.constant.ServiceConstant.DEFAULT_IMAGE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class ProfileInfo {
    private static final String ORIGINAL = "0:";
    private static final String NICKNAME_SET_SPLITTER = "&";
    private static final String ID_NAME_SPLITTER = ":";
    private static final int ID_KEY = 0;
    private static final int NICKNAME_KEY = 1;
    private static final int ORIGINAL_NICKNAME_INDEX = 2;

    // 0:name&targetId:name&targetId:name ...
    private String nickname;
    private String roleInFamily;
    private String imageLink;
    private LocalDateTime modifiedDate;
    private String content;
    private Integer emoji;

    @Builder
    public ProfileInfo(String firstNickname, String roleInFamily) {
        EntityParameterUtils.assertAbles(firstNickname);
        this.nickname = draft(firstNickname);

        EntityParameterUtils.assertAble(roleInFamily);
        this.roleInFamily = roleInFamily;

        this.imageLink = DEFAULT_IMAGE;
        this.modifiedDate = LocalDateTime.now();
        this.emoji = 0;
    }

    private String draft(String firstNickname) {
        return ORIGINAL + firstNickname;
    }

    public void patch(String nickname, String imageLink, String roleInFamily) {
        String originalNickname = originalNickname();
        if (EntityParameterUtils.assertPatch(originalNickname, nickname)) {
            newOriginal(originalNickname, nickname);
        }

        // patch imageLink
        if (imageLink != null) {
            if (imageLink.isBlank()) {
                this.imageLink = DEFAULT_IMAGE;
            } else {
                this.imageLink = imageLink;
            }
        }

        if (EntityParameterUtils.assertPatch(this.roleInFamily, roleInFamily)) {
            this.roleInFamily = roleInFamily;
        }
    }

    private void newOriginal(String asIs, String toBe) {
        nickname = nickname.replace(asIs, toBe);
    }

    public String originalNickname() {
        return this.nickname.substring(ORIGINAL_NICKNAME_INDEX);
    }

    public String getNicknameFromOther(Long otherUserId) {
        return Arrays.stream(this.nickname.split(NICKNAME_SET_SPLITTER))
                .filter(nicknameSet -> getIdFromSet(nicknameSet) == otherUserId)
                .findAny()
                .orElse(originalNickname())
                .split(ID_NAME_SPLITTER)[NICKNAME_KEY];
    }

    private long getIdFromSet(String nicknameSet) {
        return Long.parseLong(nicknameSet.split(ID_NAME_SPLITTER)[ID_KEY]);
    }

    public void putNewNickname(Long otherUserId, String newNickname) {
        String prefix = otherUserId + ":";

        if (this.nickname.contains(prefix)) {
            for (String nameSet : nickname.split(NICKNAME_SET_SPLITTER)) {
                if (nameSet.startsWith(prefix)) {
                    this.nickname = this.nickname.replace(nameSet, prefix + newNickname);
                }
            }
            return;
        }

        nickname = nickname.concat(prefix + newNickname);
    }

    public void updateContent(String content) {
        this.content = content;
        modifiedDate = LocalDateTime.now();
    }

    public void updateEmoji(int emoji) {
        this.emoji = emoji;
        modifiedDate = LocalDateTime.now();
    }
}
