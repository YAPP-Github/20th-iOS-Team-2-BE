package com.yapp.realtime.entity.util;

import com.yapp.realtime.error.exception.ErrorCode;
import com.yapp.realtime.error.exception.PersistenceException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Author : daehwan2yo
 * Date : 2022/08/07
 * Info :
 **/
public class EntityParameterUtils {

    public static void assertNull(Object object) {
        if (Objects.isNull(object)) {
            throw new PersistenceException(ErrorCode.ENTITY_PARAMETER_NOT_ACCEPT);
        }
    }

    public static void assertNulls(Object... objects) {
        if (Arrays.stream(objects)
                .anyMatch(EntityParameterUtils::notAvailable)) {
            throw new PersistenceException(ErrorCode.ENTITY_PARAMETER_NOT_ACCEPT);
        }
    }

    public static void assertAble(String toBe) {
        if (notAvailable(toBe)) {
            throw new PersistenceException(ErrorCode.ENTITY_PARAMETER_NOT_ACCEPT);
        }
    }

    public static void assertAbles(String... toBes) {
        if (Arrays.stream(toBes)
                .anyMatch(EntityParameterUtils::notAvailable)) {
            throw new PersistenceException(ErrorCode.ENTITY_PARAMETER_NOT_ACCEPT);
        }
    }

    private static boolean notAvailable(Object obj) {
        return Objects.isNull(obj);
    }

    private static boolean notAvailable(String toBe) {
        return Objects.isNull(toBe) || toBe.isBlank();
    }

    public static boolean assertPatch(String asIs, String toBe) {
        return toBe != null && !asIs.equals(toBe);
    }
}
