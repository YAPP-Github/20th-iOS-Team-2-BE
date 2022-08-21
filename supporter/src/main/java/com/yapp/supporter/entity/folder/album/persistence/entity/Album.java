package com.yapp.supporter.entity.folder.album.persistence.entity;

import com.yapp.supporter.entity.common.BaseEntity;
import com.yapp.supporter.entity.family.persistence.entity.Family;
import com.yapp.supporter.entity.file.persistence.entity.File;
import com.yapp.supporter.entity.util.EntityParameterUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.yapp.supporter.constant.EntityConstant.DEFAULT_TITLE_POSTFIX;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "ALBUM")
@NoArgsConstructor(access = PROTECTED)
public class Album extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thumbnail;
    private String title;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Family family;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<File> files;

    // need test
    @Builder
    private Album(Family family, LocalDate date) {
        this.family = family;
        this.date = date;
        this.title = defaultTitle(date);
    }

    private String defaultTitle(LocalDate dateTime) {
        String createdDate = dateTime.format(ISO_LOCAL_DATE);
        return createdDate + DEFAULT_TITLE_POSTFIX;
    }

    public boolean noThumbnail() {
        return Objects.isNull(thumbnail);
    }

    public void modifyTitle(String toBe) {
        EntityParameterUtils.assertAble(toBe);

        this.title = toBe;
    }

    // need test
    public void modifyDate(LocalDateTime dateTime) {
        EntityParameterUtils.assertNull(dateTime);
        this.date = dateTime.toLocalDate();
    }

    public void updateThumbnail(String thumbnail) {
        EntityParameterUtils.assertAble(thumbnail);

        this.thumbnail = thumbnail;
    }

    // need test
    public boolean contains(File file) {

        return files.contains(file);
    }

    // need test
    public void removeFile(File file) {
        files.remove(file);
    }
}
