
package jp.co.app.testcase;

import static jp.co.app.commons.PropertyKey.TSUBUYAKI;
import static jp.co.app.commons.PropertyKey.USER;

import jp.co.app.commons.Properties;
import jp.co.app.results.commands.CommandResult;
import jp.co.app.web.NetClient;
import jp.co.app.parameters.tests.TestParameter;
import jp.co.app.commands.Operation;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.commands.Command;
import jp.co.app.commands.CheckMutterCommand;
import jp.co.app.results.commands.CheckMutterCommandResult;

import jp.co.app.results.dao.Mutter;

public class RegisterTsubuyakiTestCase extends TestCase {
	private CommandResult expected;
	private Command tergetCommand;
	private Command setUpCommand;

	public RegisterTsubuyakiTestCase(NetClient client) {
		var user = (String)Properties.of().get(USER);
		var comment = (String)Properties.of().get(TSUBUYAKI);

		this.tergetCommand = CheckMutterCommand.of(user, comment);
		param = TestParameter.builder()
			.operation(Operation.of(
									this.tergetCommand
									))
		.build();

		expected = CheckMutterCommandResult.builder()
			.mutter(new Mutter(0, user, comment))
		 	.build();
		title = "10.投稿内容がDBで管理されている";
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
			var actMutter = CheckMutterCommandResult.class.cast(actual).getMutter();
			var expMutter = CheckMutterCommandResult.class.cast(expected).getMutter();
			var comment = "";
			if (actMutter == null) {
				comment = "target is empty.";
			} else {
				comment += "expect: user=" + expMutter.getUserName() + ", text=" + expMutter.getText() + "\n";
				comment += "actual: user=" + actMutter.getUserName() + ", text=" + actMutter.getText() + "\n";
			}
			this.testResult = TestResult.build().status(ResultStatus.WA).message(comment).build();
		}
	}
	
	public static TestCase of(NetClient client) {
		return new MainPageTestCase(client);
	}

}

