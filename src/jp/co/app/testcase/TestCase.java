package jp.co.app.testcase;

import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.parameters.tests.TestParameter;

public abstract class TestCase {
	protected TestParameter param;
	protected String title;
	protected TestResult testResult;
	
	protected abstract void setUp();
	protected abstract void tearDown();
	protected abstract void run();

	public TestCase test() {
		setUp();
		run();
		tearDown();
		return this;
	}

	public TestResult getResult() {
		return this.testResult;
	}

	public void outResult() {
		System.out.println(this.toString());
	}
	@Override
	public String toString() {
		final String SEP = " ";
		final String LF = "\n";
		StringBuilder sb = new StringBuilder("Case");
		sb.append(SEP);
		sb.append(title);
		sb.append(SEP);
		sb.append("[");
		if ( testResult.getStatus().equals(ResultStatus.AC) ) {
			sb.append("OK");
			sb.append("]");
		} else {
			sb.append("NG");
			sb.append("]");
			sb.append(LF);
			sb.append(testResult.getMessage());
		}
		return sb.toString();
	}
}

