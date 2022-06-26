package com.yapp.api.domain.calendar.element.appointment.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Appointment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String title;
	private String content;
	private String date;
	private String time;
	private boolean allDay;
	private String color;

	@ManyToOne(fetch = LAZY)
	private Family family;

	@OneToOne(fetch = LAZY)
	private User owner;

	private Appointment(String title,
						String content,
						String date,
						String time,
						boolean allDay,
						String color,
						Family family,
						User owner) {
		this.title = title;
		this.content = content;
		this.date = date;
		this.time = time;
		this.allDay = allDay;
		this.color = color;
		this.family = family;
		this.owner = owner;
	}

	public static Appointment of(String title,
								 String content,
								 String date,
								 String time,
								 boolean allDay,
								 String color,
								 Family family,
								 User owner) {
		if (allDay) {
			return new Appointment(title, content, date, "", allDay, color, family, owner);
		}
		return new Appointment(title, content, date, time, allDay, color, family, owner);
	}
}
