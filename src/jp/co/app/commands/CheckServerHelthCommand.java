package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.CheckServerHelthCommandResult;

public class CheckServerHelthCommand extends NetCommand {
	private CheckServerHelthCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.get("");
		var result = this.client.getCurrentResult();
		if ("".equals(result.getErrorMessage())) {
			// pass
		} else {
			result.setErrorMessage("Server not work. abort.\n" + result.getErrorMessage());
		}
		this.result = CheckServerHelthCommandResult.builder().httpStatus(result.getHttpStatus()).body(result.getBody())
				.errorMessage(result.getErrorMessage()).build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}

	public static CheckServerHelthCommand of(NetClient client) {
		return new CheckServerHelthCommand(client);
	}
}
