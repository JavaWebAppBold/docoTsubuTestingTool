package jp.co.app.testcase;

import static jp.co.app.commons.PropertyKey.TSUBUYAKI;
import static jp.co.app.commons.PropertyKey.USER;

import jp.co.app.commons.Properties;
import jp.co.app.commons.Random;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.Command;
import jp.co.app.commands.ClientClearCommand;
import jp.co.app.commands.LoginCommand;
import jp.co.app.commands.PostTsubuyakiCommand;
import jp.co.app.commands.ViewTsubuyakiCommand;
import jp.co.app.results.commands.ViewTsubuyakiCommandResult;

public class ViewTsubuyakiTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;
	private Command setUpCommand;

	public ViewTsubuyakiTestCase(NetClient client) {
		var comment = Random.of().toString();
		Properties.of().add(TSUBUYAKI, comment);
		
		this.setUpCommand = ClientClearCommand.of(client);
		var preCommand = LoginCommand.of(client);
		preCommand.addCommand(PostTsubuyakiCommand.of(client));
		this.tergetCommand = ViewTsubuyakiCommand.of(client);
		param = TestParameter.builder()
			.operation(Operation.of(
									preCommand,
									this.tergetCommand
									))
		.build();

		var user = Properties.of().get(USER);
		expected = ViewTsubuyakiCommandResult.builder()
			.httpStatus(200)
			.body("<p> *" + user + " *: *" + comment + " *</p>")
		 	.build();
		title = "9. 投稿した内容が閲覧できている";
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
		return new MainPageTestCase(client);
	}

}

