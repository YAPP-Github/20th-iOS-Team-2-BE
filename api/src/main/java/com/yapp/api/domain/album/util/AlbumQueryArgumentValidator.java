package com.yapp.api.domain.album.util;

import org.springframework.stereotype.Component;

import com.yapp.api.domain.common.util.validator.ArgumentValidator;

@Component(value = "albumQueryArgumentValidator")
public class AlbumQueryArgumentValidator implements ArgumentValidator {
	@Override
	public boolean equal(String original, String target) {
		return original.equals(target);
	}
}
