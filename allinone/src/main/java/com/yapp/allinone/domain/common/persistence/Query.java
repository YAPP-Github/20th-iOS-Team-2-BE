package com.yapp.allinone.domain.common.persistence;

import java.util.function.Function;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@FunctionalInterface
public interface Query<T, R> extends Function<T, R> {
}
