package com.yapp.api.domain.file.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Author : daehwan2yo
 * Date : 2022/08/14
 * Info :
 **/
public class FileRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Date {
        @NotNull(message = "파일 수정시 변경할 날짜 입력이 되지 않았습니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime date;

    }
}
