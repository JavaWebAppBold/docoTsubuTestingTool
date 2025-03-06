package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.TsubuyakiCommandResult;

// TODO operatorクラスじゃなくて、CommandListのような形にすればいいのでは？
public class TsubuyakiCommand extends Command {
	private NetClient client;
	private TsubuyakiCommandResult result;
	private TsubuyakiCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.post("Main");
		var result = this.client.getCurrentResult();
		this.result = TsubuyakiCommandResult.builder()
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
	public static TsubuyakiCommand of(NetClient client) {
		return new TsubuyakiCommand(client);
	}
}

