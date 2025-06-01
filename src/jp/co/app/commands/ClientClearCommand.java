package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.ClientClearCommandResult;

public class ClientClearCommand extends NetCommand {
	private NetClient client;

	private ClientClearCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		this.client.clear();
		this.result = ClientClearCommandResult.builder().httpStatus(200).body("").build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}

	public static ClientClearCommand of(NetClient client) {
		return new ClientClearCommand(client);
	}
}
