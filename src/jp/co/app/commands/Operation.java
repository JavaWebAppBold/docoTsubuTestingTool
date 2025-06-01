package jp.co.app.commands;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

import jp.co.app.results.commands.CommandResult;

public class Operation {
	private List<Command> commands;
	private List<CommandResult> results;

	private Operation() {
		this.commands = new ArrayList<>();
		this.results = new ArrayList<>();
	}

	public void setCommand(Command command) {
		this.commands.add(command);
	}

	public void execute() {
		this.commands.stream().forEach(c -> {
			c.executeMacro();
			this.results.add(c.getResult());
		});
	}

	public List<CommandResult> getResults() {
		return this.results;
	}

	public static Operation of(Command... commands) {
		var operation = new Operation();
		Stream.of(commands).forEach(c -> operation.setCommand(c));
		return operation;
	}
}
