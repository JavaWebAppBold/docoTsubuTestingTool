package jp.co.app.testcase;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.ClientClearCommand;
import jp.co.app.commands.Command;
import jp.co.app.commands.LoginCommand;
import jp.co.app.commands.MainPageCommand;
import jp.co.app.commands.LogoutCommand;
import jp.co.app.results.commands.LogoutCommandResult;

public class LogoutSuccessTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;
	private Command setUpCommand;
	
	public LogoutSuccessTestCase(NetClient client) {
		this.setUpCommand = ClientClearCommand.of(client);
		var preCommand = LoginCommand.of(client);
		preCommand.addCommand(MainPageCommand.of(client));
		this.tergetCommand = LogoutCommand.of(client);
		param = TestParameter.builder()
			.operation(Operation.of(
									preCommand,
									this.tergetCommand
									))
		.build();
		expected = LogoutCommandResult.builder()
			.httpStatus(200)
			.body("href=\"/docoTsubu/\"")
		 	.build();
		title = "7. 正常にログアウトでき、ログアウト画面が正常に表示されている";
	}
	
	@Override
	protected void setUp() {
		try{
			this.setUpCommand.execute();
		} catch(Throwable e) {
			System.err.println("");
			this.testResult = TestResult.build().status(ResultStatus.RE).build();
		}
	}

	@Override
	protected void tearDown() {
	}
	
	@Override
	protected void run() {
		param.getOperation().execute();
		var actual = this.tergetCommand.getResult();
		if ( expected.equals(actual) ) {
			this.testResult = TestResult.build().status(ResultStatus.AC).build();
		} else {
			this.testResult = TestResult.build().status(ResultStatus.WA).message(actual.getErrorMessage()).build();
		}
	}
	
	public static TestCase of(NetClient client) {
		return new LogoutSuccessTestCase(client);
	}

}


