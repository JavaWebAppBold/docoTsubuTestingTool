package jp.co.app.testcase;

import jp.co.app.results.tests.TestResult;
import jp.co.app.parameters.tests.TestParameter;

public abstract class TestCase {
	static final String LF = "\n";
	static final String SEP = " ";
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
		StringBuilder sb = new StringBuilder("Case");
		sb.append(SEP);
		sb.append(title);
		sb.append(SEP);
		sb.append("[");
		switch (testResult.getStatus()) {
		case AC:
			createOkString(sb);
			break;
		case WA:
			createNgString(sb, testResult);
			break;
		case RE:
			createErrorString(sb, testResult);
			break;
		default:
			createSkipString(sb);
			break;
		}
		return sb.toString();
	}

	private void createOkString(StringBuilder sb) {
		sb.append("OK");
		sb.append("]");
	}

	private void createNgString(StringBuilder sb, TestResult testResult) {
		sb.append("NG");
		sb.append("]");
		sb.append(LF);
		sb.append("テスト結果が期待値と異なります。");
		sb.append(LF);
		sb.append(testResult.getMessage());
	}

	private void createErrorString(StringBuilder sb, TestResult testResult) {
		sb.append("NG");
		sb.append("]");
		sb.append(LF);
		sb.append("検証中にエラーが発生しました。");
		sb.append(LF);
		sb.append(testResult.getMessage());
	}

	private void createSkipString(StringBuilder sb) {
		sb.append("SKIP");
		sb.append("]");
		sb.append(LF);
	}

}
