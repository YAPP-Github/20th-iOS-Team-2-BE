package com.yapp.api.domain.folder.controller.model;

import com.yapp.realtime.entity.file.persistence.entity.File;
import com.yapp.realtime.entity.folder.album.persistence.entity.Album;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class AlbumResponse {
    private static final String FAVOURITE = "favourite";
    private static final String PHOTO = "photo";
    private static final String RECORDING = "recording";

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailsAsDate {
        private String title;
        private List<AlbumDetail> elements = new ArrayList<>();

        public static DetailsAsDate of(Album album, List<AlbumDetail> details) {
            return new DetailsAsDate(album.getTitle(), details);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailsAsKind {
        private String type;
        private List<AlbumDetail> elements = new ArrayList<>();

        public static DetailsAsKind of(String kind, List<AlbumDetail> details) {
            return new DetailsAsKind(kind, details);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlbumDetail {
        private String title;
        private String kind;
        private Long fileId;
        private String date;
        private String link;
        private boolean favourite;
        private int commentCount;

        public static AlbumDetail from(File file) {
            return new AlbumDetail(file.getTitle(), file.getKind()
                    .name(), file.getId(), file.getDateTime().format(ISO_DATE_TIME),
                    file.getLink(), file.isFavourite(), file.getCommentCount());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Whether {
        private Boolean result;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Comments {
        private List<CommentElement> comments = new ArrayList<>();

        public static Comments from(List<CommentElement> list) {
            return new Comments(list);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentElement {
        private Long commentId;
        private Long writerId;
        private String profileLink;
        private String nickname;
        private String roleInFamily;
        private String created;
        private String content;
    }

    public interface Elements {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateElements extends AlbumResponse implements Elements {
        private List<AlbumInfo> albums = new ArrayList<>();

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor(access = AccessLevel.PRIVATE)
        public static class AlbumInfo {
            private Long albumId;
            private String title;
            private String thumbnail;
            private String date;

            public static AlbumInfo of(Album album) {
                return new AlbumInfo(album.getId(), album.getTitle(), album.getThumbnail(), album.getDate()
                        .format(ISO_DATE));
            }
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class KindElements extends AlbumResponse implements Elements {
        private KindDetail favourite;
        private KindDetail photo;
        private KindDetail recording;

        public static KindElements from(Map<String, List<File>> filesByKindName) {

            return new KindElements(
                    new KindDetail(FAVOURITE, getSize(filesByKindName, FAVOURITE)),
                    new KindDetail(PHOTO, getSize(filesByKindName, PHOTO)),
                    new KindDetail(RECORDING, getSize(filesByKindName, RECORDING))
            );
        }

        private static int getSize(Map<String, List<File>> filesByKindName, String recording) {
            if (Objects.isNull(filesByKindName.get(recording))) {
                return 0;
            }
            return filesByKindName.get(recording).size();
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class KindDetail {
            private String kind;
            private int count;
        }
    }
}
