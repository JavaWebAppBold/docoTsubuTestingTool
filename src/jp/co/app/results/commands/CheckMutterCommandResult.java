package jp.co.app.results.commands;

import jp.co.app.results.dao.Mutter;

public class CheckMutterCommandResult extends NetCommandResult {
	private Mutter mutter = null;

	public Mutter getMutter() {
		return this.mutter;
	}

	@Override
	public boolean equals(CommandResult other) {
		if (other == null) {
			return false;
		}
		if (!(other.getClass().equals(this.getClass()))) {
			return false;
		}
		var _other = this.getClass().cast(other);
		if (_other == null || _other.mutter == null) {
			return false;
		}
		boolean isEquals = true;
		isEquals = isEquals && (this.mutter.getUserName().equals(_other.mutter.getUserName()));
		isEquals = isEquals && (this.mutter.getText().equals(_other.mutter.getText()));
		return isEquals;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends NetCommandResult.Builder {
		private CheckMutterCommandResult result;

		public Builder() {
			super();
			result = new CheckMutterCommandResult();
		}

		public Builder mutter(Mutter mutter) {
			result.mutter = mutter;
			return this;
		}

		public CheckMutterCommandResult build() {
			return result;
		}
	}
}
