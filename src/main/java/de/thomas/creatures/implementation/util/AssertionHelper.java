package de.thomas.creatures.implementation.util;

public class AssertionHelper {
	public static void checkSmallerZero(double number, String name) throws AssertionException {
		if (number < 0) {
			throw new AssertionException(name + " must be at least 0");
		}
	}
	
	public static void checkSmallerEqualThan(double number, double checkValue, String name) throws AssertionException {
		if (number > checkValue) {
			throw new AssertionException(name + " must be at most " + checkValue);
		}
	}
	
	public static void checkGreaterEqualThan(double number, double checkValue, String name) throws AssertionException {
		if (number < checkValue) {
			throw new AssertionException(name + " must be at least " + checkValue);
		}
	}
}
