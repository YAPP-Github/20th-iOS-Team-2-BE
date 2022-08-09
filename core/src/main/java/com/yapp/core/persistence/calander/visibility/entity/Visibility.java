package com.yapp.core.persistence.calander.visibility.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.yapp.core.persistence.calander.appointment.entity.Appointment;
import com.yapp.core.persistence.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Visibility {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private boolean whether;

	@ManyToOne(fetch = LAZY)
	private User user;

	@ManyToOne(fetch = LAZY)
	private Appointment appointment;

	private Visibility(User user, Appointment appointment) {
		this.user = user;
		this.appointment = appointment;
	}

	public static Visibility of(User user, Appointment appointment) {
		return new Visibility(user, appointment);
	}
}
