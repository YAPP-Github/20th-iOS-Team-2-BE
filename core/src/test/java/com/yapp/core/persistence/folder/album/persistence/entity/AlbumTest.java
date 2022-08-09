package com.yapp.core.persistence.folder.album.persistence.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/07
 * Info :
 **/
class AlbumTest {
    Album 껍데기앨범;

    @BeforeEach
    void init() {
        껍데기앨범 = Album.builder()
                .date(LocalDate.now())
                .family(null)
                .build();
    }

    @Test
    public void noThumbnail() {
        assertThat(껍데기앨범.noThumbnail()).isTrue();
    }

    @Test
    public void modifyTitle() {
        String 변경할제목 = "changed";

        껍데기앨범.modifyTitle(변경할제목);

        assertThat(껍데기앨범.getTitle()).isEqualTo(변경할제목);
    }

    @Test
    public void updateThumbnail() {
        String 변경될썸네일 = "thumbnail address";

        껍데기앨범.updateThumbnail(변경될썸네일);

        assertThat(껍데기앨범.getThumbnail()).isEqualTo(변경될썸네일);
    }
}