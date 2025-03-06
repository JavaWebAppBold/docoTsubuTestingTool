package jp.co.app;

import java.util.List;
import java.util.ArrayList;

import jp.co.app.testsuite.HansOnTestSuite;


import jp.co.app.commands.dao.MutterDAO;

public class Main {
	public static void main(String[] argv) {
		// // warファイルは既に配置済みとする
		// var testSuite = TestSuite.of("JWA/HandsOn");
		// testSuite.add(TestCase.of("Case1"));
		// testSuite.add(TestCase.of("Case2"));
		// testSuite.add(TestCase.of("Case3"));
		// testSuite.add(TestCase.of("Case4"));
		// testSuite.add(TestCase.of("Case5"));
		// testSuite.add(TestCase.of("Case6"));
		// testSuite.add(TestCase.of("Case7"));

		System.out.println(HansOnTestSuite.of().test().toString());

		var dao = new MutterDAO();
		var l = dao.findAll();
	}
}
