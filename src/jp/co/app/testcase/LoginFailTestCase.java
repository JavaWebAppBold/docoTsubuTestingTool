package jp.co.app.testcase;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.Command;
import jp.co.app.commands.LoginFailCommand;
import jp.co.app.results.commands.LoginFailCommandResult;

public class LoginFailTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;

	public LoginFailTestCase(NetClient client) {
		this.tergetCommand = LoginFailCommand.of(client);
		param = TestParameter.builder()
			.operation(Operation.of(
									this.tergetCommand
									))
		.build();
		expected = LoginFailCommandResult.builder()
			.httpStatus(200)
			.body("href=\"/docoTsubu/\"")
		 	.build();
		title = "5. ログイン結果画面（失敗）が正常に表示されている";
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
		return new LoginFailTestCase(client);
	}

}

