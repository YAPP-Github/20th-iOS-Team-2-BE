package com.yapp.core.persistance.album.util;

import org.springframework.stereotype.Component;

import com.yapp.core.persistance.util.validator.ArgumentValidator;

@Component(value = "albumQueryArgumentValidator")
public class AlbumQueryArgumentValidator implements ArgumentValidator {
	@Override
	public boolean equal(String original, String target) {
		return original.equals(target);
	}
}
