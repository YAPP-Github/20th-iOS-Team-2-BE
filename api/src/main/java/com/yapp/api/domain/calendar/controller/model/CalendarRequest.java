package com.yapp.api.domain.calendar.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarRequest {

    @Getter
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private boolean allDay;
        private LocalTime time;
        private String content;

        @NotNull
        private LocalDate date;
        @NotBlank
        private String title;
        @NotNull
        private Boolean visibility;
        @NotBlank
        private String color;
    }

    @Getter
    @Service
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        private String color;
        private boolean allDay;
        private LocalDate date;
        private LocalTime time;
        private String content;
        private String title;
    }
}
