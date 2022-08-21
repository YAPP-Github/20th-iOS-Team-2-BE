package com.yapp.supporter.entity.file.persistence.entity;

import com.yapp.supporter.constant.FileKind;
import com.yapp.supporter.entity.common.BaseEntity;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.folder.album.persistence.entity.Album;
import com.yapp.supporter.entity.folder.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "FILE")
@NoArgsConstructor(access = PROTECTED)
public class File extends BaseEntity {
    public static final File INVALID = new File.INVALID();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String link;
    private boolean favourite;
    private LocalDateTime dateTime;

    @Enumerated(STRING)
    private FileKind kind;

    @ManyToOne(fetch = LAZY)
    private Album album;

    @ManyToOne(fetch = LAZY)
    private Family family;

    @OneToMany(mappedBy = "file", fetch = LAZY, orphanRemoval = true)
    private List<Comment> commentList;

    @Builder
    private File(String title, String link, FileKind kind, Album album, LocalDateTime dateTime, Family family) {
        this.title = title;
        this.link = link;
        this.kind = kind;
        this.album = album;
        this.dateTime = dateTime;
        this.favourite = false;
        this.family = family;
    }

    public static File recordingFile(
            String title, String link, Album album, LocalDateTime dateTime, Family family) {
        return new File(title, link, FileKind.RECORDING, album, dateTime, family);
    }

    public static File photoFile(String link, Album album, LocalDateTime dateTime, Family family) {
        return new File(null, link, FileKind.PHOTO, album, dateTime, family);
    }

    // need test
    public boolean doFavour() {
        this.favourite = !this.favourite;
        return favourite;
    }

    public boolean isPhoto() {
        return this.kind.isPhoto();
    }

    public boolean isRecording() {
        return this.kind.isRecording();
    }

    public void modifyDate(LocalDateTime date) {
        this.dateTime = date;
    }

    // need test
    public int getCommentCount() {
        return commentList.size();
    }

    // need test
    public void modifyAlbum(Album album) {
        this.album.removeFile(this);

        this.album = album;
    }

    private static class INVALID extends File {
        @Override
        public FileKind getKind() {
            return FileKind.NULL;
        }

        @Override
        public String getLink() {
            return "";
        }
    }
}
