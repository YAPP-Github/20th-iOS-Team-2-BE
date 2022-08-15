package com.yapp.api.domain.folder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlbumRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Upload {
        @NotNull(message = "파일 업로드시 날짜 입력이 되지 않았습니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime date;
        private String title;

        @NotEmpty(message = "파일 링크가 입력되지 않았습니다.")
        private List<String> links = new ArrayList<>();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        @NotBlank(message = "변경할 앨범 이름이 입력되지 않았습니다.")
        private String albumName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        @NotBlank(message = "댓글 내용이 입력되지 않았습니다.")
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyDate {
        @NotNull(message = "앨범의 변경할 날짜가 입력되지 않았습니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime date;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Delegate {
        @NotNull(message = "대표 파일을 어떤것으로 할지 입력되지 않았습니다.")
        private Long fileId;
    }
}
