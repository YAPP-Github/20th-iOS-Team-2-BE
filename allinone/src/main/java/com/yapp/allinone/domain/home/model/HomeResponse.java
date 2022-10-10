package com.yapp.allinone.domain.home.model;

import com.yapp.supporter.entity.calander.appointment.entity.Appointment;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class HomeResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeInfo {
        private String familyName;
        private List<EventInfo> events = new ArrayList<>();

        public static HomeInfo of(Family family, List<Appointment> appointments) {
            return new HomeInfo(family.getName(), appointments.stream()
                    .map(appointment -> new EventInfo(appointment.getTitle(), appointment.getDate()
                            .format(DateTimeFormatter.ISO_LOCAL_DATE), appointment.getColor()))
                    .collect(Collectors.toList()));
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EventInfo {
            private String title;
            private String eventDate;
            private String color;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Notifications {
        private List<Notification> notifications = new ArrayList<>();

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Notification {
            private String type;
            private String content;
            private Long targetId;
            private String createdDate;
        }
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id {
        private Long familyId;
    }
}
