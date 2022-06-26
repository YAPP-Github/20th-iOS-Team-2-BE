package com.yapp.pojo.unit.calendar;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.calendar.element.visibility.persistence.entity.Visibility;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Calendar : Entity 단위 테스트")
public class CalendarTest {
	@ParameterizedTest
	@ValueSource(booleans = {true, false})
	void 정상_AppointmentConstructor_생성_하루종일(boolean 하루종일) {
		Family 가족 = EntityFactory.family();
		User 만든이 = EntityFactory.user();
		String 제목 = "일정제목";
		String 내용 = "일정내용";
		String 날짜 = "2022-06-20";
		String 시간 = "20:35";
		String 색상 = "BLUE";

		Appointment 일정 = Appointment.of(제목, 내용, 날짜, 시간, 하루종일, 색상, 가족, 만든이);

		assertThat(일정.getOwner()).isEqualTo(만든이);
		assertThat(일정.getFamily()).isEqualTo(가족);
		assertThat(일정.getTitle()).isEqualTo(제목);
		assertThat(일정.getContent()).isEqualTo(내용);
		assertThat(일정.getDate()).isEqualTo(날짜);

		if (하루종일) {
			assertThat(일정.getTime()).isEqualTo("");
			assertThat(일정.isAllDay()).isEqualTo(하루종일);
		}
		if (!하루종일) {
			assertThat(일정.getTime()).isEqualTo(시간);
			assertThat(일정.isAllDay()).isEqualTo(하루종일);

		}
	}

	@Test
	void 정상_VisibilityConstructor_생성() {
		User 회원 = EntityFactory.user();
		Appointment 일정 = EntityFactory.appointment(회원);

		Visibility 일정공개여부 = Visibility.of(회원, 일정);

		assertThat(일정공개여부.getUser()).isEqualTo(회원);
		assertThat(일정공개여부.getAppointment()).isEqualTo(일정);
	}
}
