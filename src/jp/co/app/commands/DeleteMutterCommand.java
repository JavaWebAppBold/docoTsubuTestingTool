package jp.co.app.commands;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.results.commands.DeleteMutterCommandResult;

import jp.co.app.commands.dao.MutterDAO;
import jp.co.app.results.dao.Mutter;

public class DeleteMutterCommand extends NetCommand {
	private Mutter mutter = null;

	private DeleteMutterCommand(int id, String user, String comment) {
		this.mutter = new Mutter(id, user, comment);
	}

	private DeleteMutterCommand(Mutter mutter) {
		this.mutter = mutter;
	}

	@Override
	public void execute() {
		var dao = new MutterDAO();
		var isDeleted = dao.remove(this.mutter);
		this.result = DeleteMutterCommandResult.builder().deleted(isDeleted).build();
	}

	public boolean hasResult() {
		return this.result != null;
	}

	@Override
	public CommandResult getResult() {
		return this.result;
	}

	public static DeleteMutterCommand of(int id, String user, String comment) {
		return new DeleteMutterCommand(id, user, comment);
	}

	public static DeleteMutterCommand of(Mutter mutter) {
		return new DeleteMutterCommand(mutter);
	}
}
