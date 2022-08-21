package com.yapp.realtime.error.util;

/**
 * Author : daehwan2yo
 * Date : 2022/08/07
 * Info :
 **/
@FunctionalInterface
public interface Validator<V, E extends RuntimeException> {
    E validate(V value);
}
