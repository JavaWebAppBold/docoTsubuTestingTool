package jp.co.app.testcase;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.Command;
import jp.co.app.commands.ClientClearCommand;
import jp.co.app.commands.LoginCommand;
// import jp.co.app.results.commands.LoginCommandResult;
import jp.co.app.commands.BlankTsubuyakiCommand;
import jp.co.app.results.commands.BlankTsubuyakiCommandResult;

public class BlankTsubuyakiTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;
	private Command setUpCommand;

	public BlankTsubuyakiTestCase(NetClient client) {
		this.setUpCommand = ClientClearCommand.of(client);
		var preCommand = LoginCommand.of(client);
		this.tergetCommand = BlankTsubuyakiCommand.of(client);
		param = TestParameter.builder()
			.operation(Operation.of(
									preCommand
									, this.tergetCommand
									))
		.build();
		expected = BlankTsubuyakiCommandResult.builder()
			.httpStatus(200)
		 	.build();
		title = "8. 正常に投稿できる、空文字で投稿してもエラーにならない";
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
			this.testResult = TestResult.build()
					.status(ResultStatus.AC).build();
		} else {
			this.testResult = TestResult.build()
					.status(ResultStatus.WA)
					.message(actual.getErrorMessage(expected, actual)).build();
		}
	}
	
	public static TestCase of(NetClient client) {
		return new BlankTsubuyakiTestCase(client);
	}

}

