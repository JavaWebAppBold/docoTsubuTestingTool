package jp.co.app;

import jp.co.app.testsuite.HansOnTestSuite;

public class Main {
	public static void main(String[] argv) {
		System.out.println(HansOnTestSuite.of().test().toString());
	}
}
