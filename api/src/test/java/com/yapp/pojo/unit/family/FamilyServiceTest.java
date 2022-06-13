package com.yapp.pojo.unit.family;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.family.persistence.handler.FamilyCommandHandlerImpl;
import com.yapp.api.domain.family.persistence.handler.FamilyQueryHandlerImpl;
import com.yapp.api.domain.family.service.FamilyService;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;
import com.yapp.util.Mocker;

@DisplayName("Family : Service 단위 테스트")
public class FamilyServiceTest extends Mocker {
	private FamilyService familyService;

	@BeforeEach
	void init() {
		familyService = new FamilyService(new FamilyCommandHandlerImpl(familyRepository), new FamilyQueryHandlerImpl());
	}

	@Test
	void 정상_create_생성() {
		String 가족이름 = "가족이름";
		String 가훈 = "가훈";
		User 만든이 = EntityFactory.user();
		willReturn(new Family(만든이, 가족이름, 가훈)).given(familyRepository)
											 .save(any());

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
}
