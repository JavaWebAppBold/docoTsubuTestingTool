package jp.co.app.results.commands;

import jp.co.app.results.dao.Mutter;

public class DeleteMutterCommandResult extends NetCommandResult {
	private boolean deleted = false;

	public boolean isDeleted() {
		return this.deleted;
	}

	@Override
	public boolean equals(CommandResult other) {
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}
	public static class Builder extends NetCommandResult.Builder {
		private DeleteMutterCommandResult result;
		public Builder() {
			super();
			result = new DeleteMutterCommandResult();
		}

		public Builder deleted(boolean deleted) {
			result.deleted = deleted;
			return this;
		}

		public DeleteMutterCommandResult build() {
			return result;
		}
	}
}

