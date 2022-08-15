package com.yapp.core.util.validator;

import org.springframework.stereotype.Component;

import com.yapp.core.util.validator.ArgumentValidator;

@Component(value = "albumQueryArgumentValidator")
public class AlbumQueryArgumentValidator implements ArgumentValidator {
	@Override
	public boolean equal(String original, String target) {
		return original.equals(target);
	}
}
