package jp.co.app.testsuite;

import static jp.co.app.commons.PropertyKey.USER;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import jp.co.app.commons.Url;
import jp.co.app.commons.Random;
import jp.co.app.commands.CheckServerHelthCommand;
import jp.co.app.commons.Properties;
import jp.co.app.web.NetClient;
import jp.co.app.results.ResultStatus;
import jp.co.app.results.tests.TestResult;
import jp.co.app.testcase.TestCase;
import jp.co.app.testcase.TopPageTestCase;
import jp.co.app.testcase.LoginSuccessTestCase;
import jp.co.app.testcase.MainPageTestCase;
import jp.co.app.testcase.BlankTsubuyakiTestCase;
import jp.co.app.testcase.ViewTsubuyakiTestCase;
import jp.co.app.testcase.RegisterTsubuyakiTestCase;
import jp.co.app.testcase.LogoutSuccessTestCase;
import jp.co.app.testcase.LoginFailTestCase;

public class HansOnTestSuite extends TestSuite {
	private Url domain;
	private String user;
	private NetClient client;
	private List<Class<? extends TestCase>> testCaseClasses;
	
	private HansOnTestSuite() {
		// TODO 実行時間で見て？登録したデータを削除する→データが逼迫すると思う
		
		this.testCaseClasses = new ArrayList<>(){
				{
					//  3. トップ画面が正常に表示されている
					add(TopPageTestCase.class);
					//  4. ログイン結果画面（成功）が正常に表示されている
					add(LoginSuccessTestCase.class);
					//  6. メイン画面が正常に表示されている
					add(MainPageTestCase.class);
					//  8. 正常に投稿できる、空文字で投稿してもエラーにならない
					add(BlankTsubuyakiTestCase.class);
					//  9. 投稿した内容が閲覧できている
					add(ViewTsubuyakiTestCase.class);
					// 10.投稿内容がDBで管理されている（P396〜407の実装）
					add(RegisterTsubuyakiTestCase.class);
					//  7. 正常にログアウトでき、ログアウト画面が正常に表示されている
					add(LogoutSuccessTestCase.class);
					//  5. ログイン結果画面（失敗）が正常に表示されている
					add(LoginFailTestCase.class);
				}
			};		
	}
	
	@Override
	protected void setUp() {
		// URL/URIに対応する
		this.domain = Url.of("http","localhost", "8080");
		domain.addPath("docoTsubu");

		var props = Properties.of();
		var user = Random.of().toString();
		props.add(USER, user);

		this.client = NetClient.of(domain);
		testCases = new ArrayList<>();
	}
	@Override
	protected void tearDown() {
	}
	@Override
	protected void run() {
		// TOOD サーバ起動チェックを入れる
		var cmd = CheckServerHelthCommand.of(this.client);
		cmd.execute();
		if ("".equals(cmd.getResult().getErrorMessage())) {
			// pass
		} else {
			var result = new TestCase() {
					@Override
					protected void setUp() {
					}
					
					@Override
					protected void tearDown() {
					}
					
					@Override
					protected void run() {
						this.testResult = TestResult.build()
							.status(ResultStatus.RE)
							.message(cmd.getResult().getErrorMessage())
							.build();
					}

				};
			result.run();
			this.testCases.add(result);
			return;
		}

		this.testCaseClasses.forEach(t->{
				try{
					var c = t.getConstructor(NetClient.class);
					var o = c.newInstance(this.client);
					o.test();
					this.testCases.add(o);
				} catch(Throwable e) {
					System.err.println(e);
					System.err.println("SKIP: " + t.getName());
				}
			});
	}

	public List<TestResult> getTestResult() {
		// return testCases.stream().map(t->t.getResult()).toList();
		return testCases.stream().map(t->t.getResult()).collect(Collectors.toList());
	}

	public static HansOnTestSuite of() {
		return new HansOnTestSuite();
	}
}

