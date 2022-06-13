package com.yapp.pojo.unit.family;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.core.error.exception.BaseBusinessException;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("Family : Service 단위 테스트")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FamilyServiceTest extends Mocker {
	private FamilyService familyService;

	@BeforeAll
	void init() {
		familyService = new FamilyService(familyCommandHandler, familyQueryHandler);
	}

	@Test
	void 정상_create_생성() {
		String 가족이름 = "가족이름";
		String 가훈 = "가훈";
		User 만든이 = EntityFactory.user();

		Family 가족 = familyService.create(만든이, 가족이름, 가훈);

		assertThat(가족.getName()).isEqualTo(가족이름);
		assertThat(가족.getMotto()).isEqualTo(가훈);
		assertThat(가족.getOwner()).isEqualTo(만든이);
		assertThat(가족.getMemberCount()).isEqualTo(1);
	}

	@Test
	void 정상_modify_가족정보수정() {
		User 수정자 = EntityFactory.user();
		Family 가족 = EntityFactory.family(수정자);
		String 수정할_이미지 = "imageLink";
		String 수정할_가족이름 = "변경될 이름";
		String 수정할_가훈 = "변경될 가훈";

		familyService.modify(수정자, 수정할_이미지, 수정할_가족이름, 수정할_가훈);

		assertThat(가족.getName()).isEqualTo(수정할_가족이름);
		assertThat(가족.getMotto()).isEqualTo(수정할_가훈);
		assertThat(가족.getImageLink()).isEqualTo(수정할_이미지);
	}

	@ParameterizedTest
	@MethodSource("getBadRequestForCreateFamily")
	void 예외_create_올바르지못한_생성접근(FamilyCreateCondition badCondition) {
		assertThatThrownBy(() -> familyService.create(badCondition.getUser(),
													  badCondition.getFamilyName(),
													  badCondition.getFamilyMotto())).isInstanceOf(BaseBusinessException.class);
	}

	private Set<FamilyCreateCondition> getBadRequestForCreateFamily() {
		return Set.of(new FamilyCreateCondition(EntityFactory.anonymousUser(), "가족이름", "가족가훈"),
					  new FamilyCreateCondition(EntityFactory.user(), "", "가족가훈"),
					  new FamilyCreateCondition(EntityFactory.user(), "가족이름", ""),
					  new FamilyCreateCondition(EntityFactory.user(), "", ""));
	}

	private class FamilyCreateCondition {
		User user;
		String familyName;
		String familyMotto;

		FamilyCreateCondition(User user, String familyName, String familyMotto) {
			this.user = user;
			this.familyName = familyName;
			this.familyMotto = familyMotto;
		}

		public User getUser() {
			return user;
		}

		public String getFamilyName() {
			return familyName;
		}

		public String getFamilyMotto() {
			return familyMotto;
		}
	}
}
