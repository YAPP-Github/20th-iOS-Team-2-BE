package com.yapp.core.persistence.calander.appointment.persistence.entity;

import com.yapp.core.persistence.common.BaseEntity;
import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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

    private Appointment(
            String title,
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

    private Appointment(
            String title, String content, String date, boolean allDay, String color, Family family, User owner) {
        this.title = title;
        this.content = content;
        this.date = LocalDate.parse(date);
        this.allDay = allDay;
        this.color = color;
        this.family = family;
        this.owner = owner;
    }

    public static Appointment of(
            String title,
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
