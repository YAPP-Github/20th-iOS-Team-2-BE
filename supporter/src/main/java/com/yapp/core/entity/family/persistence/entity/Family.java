package com.yapp.core.entity.family.persistence.entity;

import com.yapp.core.entity.common.BaseEntity;
import com.yapp.core.entity.user.entity.User;
import com.yapp.core.entity.util.EntityParameterUtils;
import com.yapp.core.util.CodeGenerateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "FAMILY")
public class Family extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String motto;
    private String imageLink;
    private String code;

    @OneToOne(fetch = LAZY)
    private User owner;

    @Embedded
    @Getter(PRIVATE)
    private final FamilyMembers familyMembers = new FamilyMembers();

    public static Family of(User user, String name, String motto) {
        Family createdFamily = Family.builder()
                .user(user)
                .name(name)
                .motto(motto)
                .build();

        createdFamily.addUser(user);
        createdFamily.code = CodeGenerateUtils.code();

        return createdFamily;
    }

    // need test
    @Builder(access = PRIVATE)
    private Family(User user, String name, String motto) {
        this.owner = user;
        this.name = name;
        this.motto = motto;
        this.code = CodeGenerateUtils.code();
    }

    // need test
    public void addUser(User user) {
        EntityParameterUtils.assertNull(user);

        user.setFamily(this);
        familyMembers.add(user);
    }

    // need test
    public int getMemberCount() {
        return familyMembers.getCount();
    }

    public void patch(String familyName, String familyMotto) {
        if (EntityParameterUtils.assertPatch(this.name, familyName)) {
            this.name = familyName;
        }

        if (EntityParameterUtils.assertPatch(this.motto, familyMotto)) {
            this.motto = familyMotto;
        }
    }

    // need test
    public Set<User> getMembers() {
        return new HashSet<>(familyMembers.getMembers());
    }

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = PRIVATE)
    public static class FamilyMembers {
        @OneToMany(mappedBy = "family", fetch = LAZY)
        private Set<User> members = new HashSet<>();

        void add(User user) {
            members.add(user);
        }

        int getCount() {
            return members.size();
        }
    }
}
