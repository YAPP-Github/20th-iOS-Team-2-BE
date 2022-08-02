package com.yapp.core.util;

import java.util.Random;

/**
 * Author : daehwan2yo
 * Date : 2022/08/02
 * Info : 
 **/
public class CodeGenerator {

	public static String code() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char)randomLimitedInt);
		}

		return buffer.toString();
	}
}
