package com.yapp.core.entity.family.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/08
 * Info :
 **/
class FamilyTest {

    @Test
    void update() {
        String 변경될_이름 = "changed";
        String 변경될_가훈 = "changed";
        String 추가할_가족사진 = "imageLink";
        Family 빈_family = Family.of(null, 변경될_이름, 변경될_가훈);


        빈_family.patch(변경될_이름, 변경될_가훈);

        assertThat(빈_family.getName()).isEqualTo(변경될_이름);
        assertThat(빈_family.getMotto()).isEqualTo(변경될_가훈);
        assertThat(빈_family.getImageLink()).isEqualTo(추가할_가족사진);
    }

}