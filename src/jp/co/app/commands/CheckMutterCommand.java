//
package jp.co.app.commands;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.CheckMutterCommandResult;

import jp.co.app.commands.dao.MutterDAO;


public class CheckMutterCommand extends NetCommand {
	private String user;
	private String comment;
	private CheckMutterCommand(String user, String comment) {
		this.user = user;
		this.comment = comment;
	}

	@Override
	public void execute() {
		var dao = new MutterDAO();
		var mutter = dao.find(this.user, this.comment);
		this.result = CheckMutterCommandResult.builder()
			.mutter(mutter)
			//			.errorMessage(result.getErrorMessage())
			.build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}
	public static CheckMutterCommand of(String user, String comment) {
		return new CheckMutterCommand(user, comment);
	}
}

