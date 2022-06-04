package com.yapp.api.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class SnsAccount {

    @Id
    @GeneratedValue()
    private Long id;

    private Long userId;

    @Enumerated(EnumType.ORDINAL)
    private SnsType snsType;

    private String accessToken;

    private String refreshToken;

    private ZonedDateTime createdAt;
}
