package com.yapp.pojo.unit.family;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.family.persistence.entity.Family;

@DisplayName("Family : Entity 단위 테스트")
public class FamilyTest {

	@Test
	void 정상_constructor_생성() {
		String 가족명 = "familyName";
		String 가훈 = "familyMotto";

		Family 가족 = new Family(가족명, 가훈);

		assertThat(가족.getName()).isEqualTo(가족명);
		assertThat(가족.getMotto()).isEqualTo(가훈);
	}
}
