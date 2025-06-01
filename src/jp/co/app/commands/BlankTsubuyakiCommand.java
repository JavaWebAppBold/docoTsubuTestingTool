package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.web.WebQuery;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.BlankTsubuyakiCommandResult;

public class BlankTsubuyakiCommand extends NetCommand {
	private NetClient client;

	private BlankTsubuyakiCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		var query = WebQuery.of();
		query.add("text", "");

		this.client.post("Main", query);
		var result = this.client.getCurrentResult();
		this.result = BlankTsubuyakiCommandResult.builder().httpStatus(result.getHttpStatus()).body(result.getBody())
				.errorMessage(result.getErrorMessage()).build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}

	public static BlankTsubuyakiCommand of(NetClient client) {
		return new BlankTsubuyakiCommand(client);
	}
}
