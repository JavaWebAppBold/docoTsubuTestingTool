package jp.co.app.commands.results;

public abstract class CommandResult {
	protected String errorMessage = "";
	public abstract boolean equals(CommandResult other);

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
