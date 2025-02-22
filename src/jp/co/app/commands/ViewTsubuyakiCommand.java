package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.ViewTsubuyakiCommandResult;

public class ViewTsubuyakiCommand extends NetCommand {
	private NetClient client;
	private ViewTsubuyakiCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.get("Main");
		var result = this.client.getCurrentResult();
		this.result = ViewTsubuyakiCommandResult.builder()
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
	public static ViewTsubuyakiCommand of(NetClient client) {
		return new ViewTsubuyakiCommand(client);
	}
}

