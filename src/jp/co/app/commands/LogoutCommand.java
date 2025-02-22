package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.LogoutCommandResult;

public class LogoutCommand extends NetCommand {
	private NetClient client;
	private LogoutCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.get("Logout");
		var result = this.client.getCurrentResult();
		this.result = LogoutCommandResult.builder()
			.httpStatus(result.getHttpStatus())
			.body(result.getBody())
			.errorMessage(result.getErrorMessage())
			.build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}
	public static LogoutCommand of(NetClient client) {
		return new LogoutCommand(client);
	}
}

