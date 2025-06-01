package jp.co.app.results.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.DOTALL;

public class NetCommandResult extends CommandResult {
	private static final String LF = "\n";
	protected int httpStatus = 0;
	protected String body = "";
	protected static NetCommandResult instance;

	@Override
	public boolean equals(CommandResult other) {
		if (!isSameClass(other)) {
			return false;
		}
		var _other = this.getClass().cast(other);
		boolean isEquals = true;
		isEquals = isEquals && (this.httpStatus == _other.httpStatus);
		if (this.body.equals(_other.body)) {
			return isEquals;
		}
		isEquals = isEquals && this.match(_other);
		return isEquals;
	}

	private boolean match(NetCommandResult other) {
		String _self = this.body.replaceAll("\n", "");
		String _other = other.body.replaceAll("\n", "");

		final Pattern ptn = Pattern.compile(_self, DOTALL);
		Matcher m = ptn.matcher(_other);
		return m.find();
	}

	@Override
	public String getErrorMessage(CommandResult expected, CommandResult actual) {
		var canCheck = true;
		canCheck = canCheck && isSameClass(expected);
		canCheck = canCheck && isSameClass(actual);
		canCheck = canCheck && isOkStatus();
		StringBuilder sb = new StringBuilder();
		if (!canCheck) {
			sb.append("エラーメッセージ:");
			sb.append(LF);
			sb.append(actual.getErrorMessage());
			return sb.toString();
		}
		var _expected = this.getClass().cast(expected);
		var _actual = this.getClass().cast(actual);
		sb.append("期待値:");
		sb.append(_expected.body);
		sb.append(LF);
		sb.append("実際値:");
		sb.append(_actual.body);
		sb.append(LF);
		return sb.toString();
	}

	private boolean isSameClass(CommandResult testee) {
		if (testee == null) {
			return false;
		}
		if (!(testee.getClass().equals(this.getClass()))) {
			return false;
		}
		var _testee = this.getClass().cast(testee);
		if (_testee == null) {
			return false;
		}
		return true;
	}

	private boolean isOkStatus() {
		return this.httpStatus >= 200 && this.httpStatus < 400;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private NetCommandResult result;

		public Builder() {
			result = new NetCommandResult();
		}

		public Builder httpStatus(int httpStatus) {
			result.httpStatus = httpStatus;
			return this;
		}

		public Builder body(String body) {
			result.body = body.trim();
			return this;
		}

		public Builder errorMessage(String errorMessage) {
			result.errorMessage = errorMessage;
			return this;
		}

		public NetCommandResult build() {
			return result;
		}
	}
}
