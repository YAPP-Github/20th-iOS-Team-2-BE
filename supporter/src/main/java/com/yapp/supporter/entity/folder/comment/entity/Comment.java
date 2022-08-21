package com.yapp.supporter.entity.folder.comment.entity;

import com.yapp.supporter.entity.common.BaseEntity;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.file.persistence.entity.File;
import com.yapp.supporter.entity.user.entity.User;
import com.yapp.supporter.entity.util.EntityParameterUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor(access = PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Family family;

    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    /**
     * Service layer 에서 Validate 할 필요가 있다.
     * user not null
     * family not null
     * file not null
     * content nullable
     */
    @Builder
    public Comment(User user, Family family, File file, String content) {
        this.user = user;
        this.family = family;
        this.file = file;
        this.content = content;
    }

    public void modify(String toBe) {
        EntityParameterUtils.assertAble(toBe);

        this.content = toBe;
    }
}
