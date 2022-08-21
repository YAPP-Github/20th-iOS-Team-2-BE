package com.yapp.realtime.error.exception;

/**
 * Author : daehwan2yo
 * Date : 2022/08/15
 * Info :
 **/
public interface ExceptionThrowableLayer {
    default String packageName(Class<?> clazz) {
        return clazz.getName();
    }
}
