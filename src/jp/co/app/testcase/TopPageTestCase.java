package jp.co.app.testcase;

import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.Command;
import jp.co.app.commands.RootPathAccessCommand;
import jp.co.app.results.commands.RootPathAccessCommandResult;

public class TopPageTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;

	public TopPageTestCase(NetClient client) {
		this.tergetCommand = RootPathAccessCommand.of(client);
		this.param = TestParameter.builder()
			.operation(Operation.of(this.tergetCommand))
			// .command(RootPathAccessCommand.of(client))
			.build();
		expected = RootPathAccessCommandResult.builder()
			.httpStatus(200)
			.body("action=\"/docoTsubu/Login\"")
			.build();
		// this.title = "トップページアクセスに成功する";
		this.title = "3. トップ画面が正常に表示されている";
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
		return new TopPageTestCase(client);
	}
}

