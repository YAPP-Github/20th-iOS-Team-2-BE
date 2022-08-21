package com.yapp.supporter.entity.calander.appointment.entity;

import com.yapp.supporter.entity.common.BaseEntity;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.user.entity.User;
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
            LocalDate date,
            LocalTime time,
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

    private Appointment(
            String title, String content, LocalDate date, boolean allDay, String color, Family family, User owner) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.allDay = allDay;
        this.color = color;
        this.family = family;
        this.owner = owner;
    }

    public static Appointment of(
            String title,
            String content,
            LocalDate date,
            LocalTime time,
            boolean allDay,
            String color,
            Family family,
            User owner) {
        if (allDay) {
            return new Appointment(title, content, date, allDay, color, family, owner);
        }
        return new Appointment(title, content, date, time, allDay, color, family, owner);
    }

    public void patch(String title, String color, String content, LocalDate date, LocalTime time) {
        if (patchAble(this.title, title)) {
            this.title = title;
        }
        if (patchAble(this.color, color)) {
            this.color = color;
        }
        if (patchAble(this.content, content)) {
            this.content = content;
        }
        if (patchAble(this.date, date)) {
            this.date = date;
        }
        if (patchAble(this.time, time)) {
            this.time = time;
        }
    }

    private boolean patchAble(Object asIs, Object toBe) {
        return (toBe != null && !asIs.equals(toBe));
    }
}
