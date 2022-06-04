package com.yapp.api.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue()
    private Long id;

    private Long familyId;

    private String nickname;

    private String name;

    private String birth;

    @Enumerated(EnumType.ORDINAL)
    private Relationship relationship;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
