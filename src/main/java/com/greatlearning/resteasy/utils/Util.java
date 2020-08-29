package com.greatlearning.resteasy.utils;

import java.util.UUID;

public class Util {

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String generateGUID() {
		return UUID.randomUUID().toString();
	}
}
