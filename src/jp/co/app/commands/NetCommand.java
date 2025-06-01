package jp.co.app.commands;

import jp.co.app.web.NetClient;
import jp.co.app.results.commands.NetCommandResult;

public abstract class NetCommand extends Command {
	protected NetClient client;
	protected NetCommandResult result;
}
