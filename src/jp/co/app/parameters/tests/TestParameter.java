package jp.co.app.parameters.tests;

import jp.co.app.commands.Operation;
import jp.co.app.commands.Command;

public class TestParameter {
	private Operation operation;
	private Command command;

	private TestParameter() {
	}
	public Operation getOperation() {
		return this.operation;
	}
	public Command getCommand() {
		return this.command;
	}
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder{
		private TestParameter params;
		public Builder() {
			params = new TestParameter();
		}
		public TestParameter build() {
			return params;
		}
		public Builder operation(Operation operation) {
			params.operation = operation;
			return this;
		}
		public Builder command(Command command) {
			params.command = command;
			return this;
		}
	}
}

