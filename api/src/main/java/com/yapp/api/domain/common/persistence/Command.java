package com.yapp.api.domain.common.persistence;

import java.util.function.Consumer;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
@FunctionalInterface
public interface Command<T> extends Consumer<T> {
}
