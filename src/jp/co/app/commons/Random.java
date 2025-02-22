package jp.co.app.commons;

public class Random {
	private final int DIGITS = 100000000;
	private Integer generate() {
		return (int)Math.floor(Math.random()*DIGITS);
	}
	public String toString() {
		return generate().toString();
	}
	public static Random of() {
		return new Random();
	}
}

