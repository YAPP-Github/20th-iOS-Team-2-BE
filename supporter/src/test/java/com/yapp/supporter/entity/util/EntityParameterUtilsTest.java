package com.yapp.supporter.entity.util;

import com.yapp.supporter.error.exception.PersistenceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Author : daehwan2yo
 * Date : 2022/08/07
 * Info :
 **/
class EntityParameterUtilsTest {

    @Test
    public void assertAble() {
        assertThrows(PersistenceException.class, () -> EntityParameterUtils.assertNull(null));
        assertThrows(PersistenceException.class, () -> EntityParameterUtils.assertAble(""));
        assertThrows(PersistenceException.class, () -> EntityParameterUtils.assertAbles("", null));
        assertThrows(PersistenceException.class, () -> EntityParameterUtils.assertNulls("", null));
    }
}