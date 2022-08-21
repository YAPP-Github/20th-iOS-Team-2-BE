package com.yapp.supporter.entity.family.persistence.entity;

import com.yapp.supporter.mock.Mocker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/08
 * Info :
 **/
@DisplayName("[Unit] Family")
class FamilyTest {

    @Test
    void update() {
        String 변경될_이름 = "changed";
        String 변경될_가훈 = "changed";
        Family 빈_family = Family.of(Mocker.user(), 변경될_이름, 변경될_가훈);

        빈_family.patch(변경될_이름, 변경될_가훈);

        assertThat(빈_family.getName()).isEqualTo(변경될_이름);
        assertThat(빈_family.getMotto()).isEqualTo(변경될_가훈);
    }

}