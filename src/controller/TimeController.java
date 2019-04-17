package controller;

import rule.ThreadRule;

public class TimeController {
	public static long startingTime = System.currentTimeMillis();
	
	public static long getCurrentTime() {
		return System.currentTimeMillis() - startingTime;
	}
}
