package com.yapp.core.persistance.calander.appointment.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.yapp.core.persistance.BaseEntity;
import com.yapp.core.persistance.family.persistence.entity.Family;
import com.yapp.core.persistance.user.entity.User;

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
	private LocalDate date;
	private LocalTime time;
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
		this.date = LocalDate.parse(date);
		this.time = LocalTime.parse(time);
		this.allDay = allDay;
		this.color = color;
		this.family = family;
		this.owner = owner;
	}

	private Appointment(String title,
						String content,
						String date,
						boolean allDay,
						String color,
						Family family,
						User owner) {
		this.title = title;
		this.content = content;
		this.date = LocalDate.parse(date);
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
			return new Appointment(title, content, date, allDay, color, family, owner);
		}
		return new Appointment(title, content, date, time, allDay, color, family, owner);
	}
}
