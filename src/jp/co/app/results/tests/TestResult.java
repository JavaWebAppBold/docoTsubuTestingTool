package jp.co.app.results.tests;

import jp.co.app.results.ResultStatus;

public class TestResult {
	private ResultStatus status = ResultStatus.WJ;
	private String message = "";
	private TestResult() {}

	public ResultStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}

	public static TestResultBuilder build() {
		return new TestResultBuilder();
	}

	public static class TestResultBuilder {
		private TestResult result;
		public TestResultBuilder() {
			result = new TestResult();
		}
		public TestResultBuilder status(ResultStatus status) {
			result.status = status;
			return this;
		}
		public TestResultBuilder message(String message) {
			result.message = message;
			return this;
		}

		public TestResult build() {
			return result;
		}
	}
}
