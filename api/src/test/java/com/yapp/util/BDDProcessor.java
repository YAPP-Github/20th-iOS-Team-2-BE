package com.yapp.util;

import java.util.function.Consumer;
import java.util.function.Function;

public interface BDDProcessor<T, R> {
	default void whenCommand(T t, Consumer<T> consumer) {
		consumer.accept(t);
	}

	default R whenQuery(T t, Function<T, R> function) {
		return function.apply(t);
	}

	default void then(Runnable runnable) {
		runnable.run();
	}
}
