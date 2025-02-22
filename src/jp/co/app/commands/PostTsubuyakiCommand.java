package jp.co.app.commands;

import static jp.co.app.commons.PropertyKey.TSUBUYAKI;
import jp.co.app.commons.Properties;

import jp.co.app.web.NetClient;
import jp.co.app.web.WebQuery;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.PostTsubuyakiCommandResult;

public class PostTsubuyakiCommand extends NetCommand {
	private NetClient client;
	private PostTsubuyakiCommand(NetClient client) {
		this.client = client;
	}

	@Override
	public void execute() {
		var comment = Properties.of().get(TSUBUYAKI);
		var query = WebQuery.of();
		query.add("text", String.class.cast(comment));

		this.client.post("Main", query);
		var result = this.client.getCurrentResult();
		this.result = PostTsubuyakiCommandResult.builder()
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
	public static PostTsubuyakiCommand of(NetClient client) {
		return new PostTsubuyakiCommand(client);
	}
}

