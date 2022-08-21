package com.yapp.realtime.util.validator;

import org.springframework.stereotype.Component;

@Component(value = "albumQueryArgumentValidator")
public class AlbumQueryArgumentValidator implements ArgumentValidator {
	@Override
	public boolean equal(String original, String target) {
		return original.equals(target);
	}
}
