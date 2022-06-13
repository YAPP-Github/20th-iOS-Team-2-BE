package com.yapp.pojo.unit.calendar;

import static java.time.LocalDateTime.*;
import static java.time.format.DateTimeFormatter.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.yapp.api.domain.calendar.element.appointment.persistence.entity.Appointment;
import com.yapp.api.domain.calendar.element.visibility.persistence.entity.Visibility;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;
import com.yapp.util.EntityFactory;

@DisplayName("Calendar : Entity 단위 테스트")
public class CalendarTest {
	@Test
	void 정상_AppointmentConstructor_생성() {
		Family 가족 = EntityFactory.family();
		User 만든이 = EntityFactory.user();
		String 제목 = "일정제목";
		String 내용 = "일정내용";
		String 날짜 = "2022-06-13T20:00";

		Appointment 일정 = new Appointment(가족, 만든이, 제목, 내용, parse(날짜, ISO_DATE_TIME));

		assertThat(일정.getOwner()).isEqualTo(만든이);
		assertThat(일정.getFamily()).isEqualTo(가족);
		assertThat(일정.getTitle()).isEqualTo(제목);
		assertThat(일정.getContent()).isEqualTo(내용);
		assertThat(일정.getDate()).isEqualTo(parse(날짜, ISO_DATE_TIME));
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
