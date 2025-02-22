package jp.co.app.results.commands;

public abstract class CommandResult {
	protected String errorMessage = "";
	public abstract boolean equals(CommandResult other);

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
