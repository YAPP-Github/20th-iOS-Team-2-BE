package com.yapp.pojo.unit.family;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Family : Entity 단위 테스트")
public class FamilyTest {

	@Test
	void 정상_constructor_생성() {
		User 만든이 = EntityFactory.user();
		String 가족명 = "familyName";
		String 가훈 = "familyMotto";

		Family 가족 = new Family(만든이, 가족명, 가훈);

		assertThat(가족.getOwner()).isEqualTo(만든이);
		assertThat(가족.getName()).isEqualTo(가족명);
		assertThat(가족.getMotto()).isEqualTo(가훈);
	}
}
