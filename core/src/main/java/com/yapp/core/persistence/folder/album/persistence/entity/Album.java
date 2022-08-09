package com.yapp.core.persistence.folder.album.persistence.entity;

import com.yapp.core.persistence.common.BaseEntity;
import com.yapp.core.persistence.family.persistence.entity.Family;
import com.yapp.core.persistence.file.persistence.entity.File;
import com.yapp.core.persistence.util.EntityParameterUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.yapp.core.constant.EntityConstant.DEFAULT_TITLE_POSTFIX;
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

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private List<File> files;

    /**
     * Service Layer 에서 검증 필요
     * family not null
     * date not null
     */
    // need test
    @Builder
    public Album(Family family, LocalDate date) {
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
    // occurred SELECT N+1 -> bulk 쿼리 생성 필요
    public void modifyDate(LocalDateTime dateTime) {
        EntityParameterUtils.assertNull(dateTime);

        this.date = dateTime.toLocalDate();
        files.forEach(file -> file.modifyDate(dateTime));
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
