package com.yapp.core.constant;

import static com.yapp.core.constant.EntityConstant.*;

/**
 * Author : daehwan2yo
 * Date : 2022/08/09
 * Info :
 **/
public enum FileKind {
    PHOTO(KIND_PHOTO), RECORDING(KIND_RECORDING), FAVOURITE(KIND_FAVOURITE), NULL(KIND_NULL);
    private final String value;

    FileKind(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean kindIsPhoto(String kindName) {
        return PHOTO.getValue()
                .equalsIgnoreCase(kindName);
    }

    public static boolean kindIsRecording(String kindName) {
        return RECORDING.getValue()
                .equalsIgnoreCase(kindName);
    }

    public boolean isPhoto() {
        return "photo".equals(value);
    }

    public boolean isRecording() {
        return "recording".equals(value);
    }
}
