package jp.co.app.testsuite;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.app.testcase.TestCase;

public abstract class TestSuite {
	protected List<TestCase> testCases;
	protected abstract void setUp();
	protected abstract void tearDown();
	protected abstract void run();

	public TestSuite test() {
		setUp();
		run();
		tearDown();
		return this;
	}

	public String toString() {
		return testCases.stream().map(t->t.toString()).collect(Collectors.joining("\n"));
	}
}

