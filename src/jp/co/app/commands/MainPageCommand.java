package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.MainPageCommandResult;

public class MainPageCommand extends NetCommand {
	private NetClient client;
	private MainPageCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.get("Main");
		var result = this.client.getCurrentResult();
		this.result = MainPageCommandResult.builder()
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
	public static MainPageCommand of(NetClient client) {
		return new MainPageCommand(client);
	}
}

