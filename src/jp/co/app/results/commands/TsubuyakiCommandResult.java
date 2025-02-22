package jp.co.app.results.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.DOTALL;

public class TsubuyakiCommandResult extends CommandResult {
	private int httpStatus = 0;
	private String body = "";

	private TsubuyakiCommandResult() {
	}

	@Override
	public boolean equals(CommandResult other) {
		// TODO リフレクションで使えないだろうか？
		var _other = (TsubuyakiCommandResult)other;
		if ( _other == null ) {
			return false;
		}
		boolean isEquals = true;
		isEquals = isEquals && (this.httpStatus == _other.httpStatus);
		isEquals = isEquals && (this.match(_other) || _other.match(this));
		return isEquals;
	}

	private boolean match(TsubuyakiCommandResult other) {
		String _self = this.body.replaceAll("\n", "");
		String _other = other.body.replaceAll("\n", "");

		final Pattern ptn = Pattern.compile(_self, DOTALL);
		Matcher m = ptn.matcher(_other);
		return m.find();
	}

	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		private TsubuyakiCommandResult result;
		public Builder() {
			result = new TsubuyakiCommandResult();
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
		public TsubuyakiCommandResult build() {
			return result;
		}
	}
}

