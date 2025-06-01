package jp.co.app.commands;

import jp.co.app.results.commands.CommandResult;

public abstract class Command {
	private Command nextCommand = null;

	public abstract void execute();

	public abstract CommandResult getResult();

	public void addCommand(Command cmd) {
		this.nextCommand = cmd;
	}

	public Command getNextCommand() {
		return this.nextCommand;
	}

	public boolean hasNextCommand() {
		return this.nextCommand != null;
	}

	public void executeMacro() {
		execute();
		if (hasNextCommand()) {
			nextCommand.executeMacro();
		}
	}
}
