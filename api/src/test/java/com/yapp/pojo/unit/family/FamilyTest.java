package com.yapp.pojo.unit.family;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Family : Entity 단위 테스트")
public class FamilyTest {
	private final User 만든이 = EntityFactory.user();
	private final String 가족명 = "familyName";
	private final String 가훈 = "familyMotto";
	private Family 가족;

	@BeforeEach
	void init() {
		가족 = new Family(만든이, 가족명, 가훈);
	}

	@Test
	void 정상_constructor_생성() {
		assertThat(가족.getOwner()).isEqualTo(만든이);
		assertThat(가족.getName()).isEqualTo(가족명);
		assertThat(가족.getMotto()).isEqualTo(가훈);
	}

	@Test
	void 정상_getMembers_가족구성원_확인() {
		User 엄마 = EntityFactory.user();
		User 아빠 = EntityFactory.user();

		가족.addMember(엄마);
		가족.addMember(아빠);

		assertThat(가족.getMembers()).containsOnly(엄마, 만든이, 아빠);
	}

	@Test
	void 정상_addMember_가족구성원_추가() {
		User 엄마 = EntityFactory.user();
		User 아빠 = EntityFactory.user();

		가족.addMember(엄마);

		assertThat(가족.getMembers()).containsOnly(만든이, 엄마);
		assertThat(가족.getMembers()).doesNotContain(아빠);
	}

	@Test
	void 정상_getMemberCount_가족구성원수_최초생성() {
		assertThat(가족.getMemberCount()).isEqualTo(1);
		assertThat(가족.getMembers()).containsOnly(만든이);
	}

	@Test
	void 정상_getMemberCount_가족구성원수_여러명참여() {
		User 엄마 = EntityFactory.user();
		User 아빠 = EntityFactory.user();

		가족.addMember(엄마);
		가족.addMember(아빠);

		assertThat(가족.getMemberCount()).isEqualTo(3);
	}
}
