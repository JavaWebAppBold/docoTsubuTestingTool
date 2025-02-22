package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.RootPathAccessCommandResult;

public class RootPathAccessCommand extends NetCommand {
	// private RootPathAccessCommand(RootPathAccessCommandParameter param) {
	// 	super(param);
	// }
	private RootPathAccessCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.post("");
		var result = this.client.getCurrentResult();
		this.result = RootPathAccessCommandResult.builder()
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

	public static RootPathAccessCommand of(NetClient client) {
		return new RootPathAccessCommand(client);
	}
}

