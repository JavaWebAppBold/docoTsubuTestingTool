package jp.co.app.commands;

import static jp.co.app.commons.PropertyKey.USER;

import jp.co.app.commons.Properties;
import jp.co.app.web.NetClient;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.LoginFailCommandResult;
import jp.co.app.web.WebQuery;

public class LoginFailCommand extends NetCommand {
	private NetClient client;

	private LoginFailCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		var query = WebQuery.of();
		var user = String.class.cast(Properties.of().get(USER));
		query.add("name", user);
		query.add("pass", "9999");

		this.client.post("Login", query);
		var result = this.client.getCurrentResult();
		this.result = LoginFailCommandResult.builder().httpStatus(result.getHttpStatus()).body(result.getBody())
				.errorMessage(result.getErrorMessage()).build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}

	public static LoginFailCommand of(NetClient client) {
		return new LoginFailCommand(client);
	}
}
