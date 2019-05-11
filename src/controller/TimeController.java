package controller;


public class TimeController {
	public static long startingTime = System.currentTimeMillis();
	
	public static long getCurrentTime() {
		return System.currentTimeMillis() - startingTime;
	}
	
	public static void resetCurrentTime() {
		startingTime = System.currentTimeMillis();
	}
}
