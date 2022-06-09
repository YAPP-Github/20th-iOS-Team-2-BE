package com.yapp.api.domain.calendar.element.appointment.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.yapp.api.domain.common.BaseEntity;
import com.yapp.api.domain.family.persistence.entity.Family;
import com.yapp.api.domain.user.persistence.entity.User;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Appointment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String title;
	private String content;
	private LocalDateTime date;

	@ManyToOne(fetch = LAZY)
	private Family family;

	@OneToOne(fetch = LAZY)
	private User owner;

	public Appointment(Family family, User user, String title, String content, LocalDateTime date) {
		this.family = family;
		this.owner = user;
		this.title = title;
		this.content = content;
		this.date = date;
	}
}
