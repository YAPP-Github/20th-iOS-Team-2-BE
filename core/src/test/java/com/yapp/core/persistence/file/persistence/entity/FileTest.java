package com.yapp.core.persistence.file.persistence.entity;

import com.yapp.core.constant.FileKind;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : daehwan2yo
 * Date : 2022/08/09
 * Info :
 **/
class FileTest {

    @Test
    void createEntity_recording() {
        String 제목 = "title";
        String 링크 = "link";

        File 음성파일 = File.recordingFile(제목, 링크, null, LocalDateTime.now(), null);

        assertThat(음성파일.getTitle()).isEqualTo(제목);
        assertThat(음성파일.getLink()).isEqualTo(링크);
        assertThat(음성파일.getDateTime()).isBefore(LocalDateTime.now());
    }

    @Test
    void createEntity_photo() {
        String 제목 = "title";
        String 링크 = "link";

        File 사진파일 = File.photoFile(제목, 링크, null, LocalDateTime.now(), null);

        assertThat(사진파일.getTitle()).isEqualTo(제목);
        assertThat(사진파일.getLink()).isEqualTo(링크);
        assertThat(사진파일.getDateTime()).isBefore(LocalDateTime.now());
    }

    @Test
    void isPhoto() {
        File 사진_파일 = File.builder()
                .kind(FileKind.PHOTO)
                .build();

        assertThat(사진_파일.isPhoto()).isTrue();
    }

    @Test
    void isRecord() {
        File 음성_파일 = File.builder()
                .kind(FileKind.RECORDING)
                .build();

        assertThat(음성_파일.isRecording()).isTrue();

    }

    @Test
    void modifyDate() {
        LocalDateTime 변경할_날짜 = LocalDateTime.of(LocalDate.of(2022, 08, 08), LocalTime.of(20, 10));
        File 기존_파일 = File.builder()
                .dateTime(LocalDateTime.now())
                .build();

        기존_파일.modifyDate(변경할_날짜);

        assertThat(기존_파일.getDateTime()).isEqualTo(변경할_날짜);
    }


}