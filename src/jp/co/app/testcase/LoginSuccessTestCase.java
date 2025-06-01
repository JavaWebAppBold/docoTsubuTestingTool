package jp.co.app.testcase;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.Command;
import jp.co.app.commands.LoginCommand;
import jp.co.app.results.commands.LoginCommandResult;

public class LoginSuccessTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;

	public LoginSuccessTestCase(NetClient client) {
		this.tergetCommand = LoginCommand.of(client);
		param = TestParameter.builder()
			.operation(Operation.of(
									this.tergetCommand
									))
		.build();
		expected = LoginCommandResult.builder()
			.httpStatus(200)
			.body("/docoTsubu/Main")
		 	.build();
		title = "4. ログイン結果画面（成功）が正常に表示されている";
	}
	
	@Override
	protected void setUp() {
	}

	@Override
	protected void tearDown() {
	}
	
	@Override
	protected void run() {
		param.getOperation().execute();
		var actual = this.tergetCommand.getResult();
		if ( expected.equals(actual) ) {
			this.testResult = TestResult.build()
					.status(ResultStatus.AC).build();
		} else {
			this.testResult = TestResult.build()
					.status(ResultStatus.WA)
					.message(actual.getErrorMessage(expected, actual)).build();
		}
	}
	
	public static TestCase of(NetClient client) {
		return new LoginSuccessTestCase(client);
	}

}

