package jp.co.app.testsuite;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.List;
// import java.util.HexFormat;
import java.util.function.Function;
import java.util.stream.Collectors;

import jp.co.app.testcase.TestCase;

public abstract class TestSuite {
	protected List<TestCase> testCases;
	protected abstract void setUp();
	protected abstract void tearDown();
	protected abstract void run();

	public TestSuite test() {
		setUp();
		run();
		tearDown();
		return this;
	}

	public String toString() {
		var sb = new StringBuilder();
		// TODO print timestamp
		sb.append(LocalDateTime.now().toString());
		sb.append("\n");
		sb.append(testCases.stream().map(t->t.toString()).collect(Collectors.joining("\n")));
		sb.append("\n");
		// TODO print Message Digest
		// TODO to command
		try{
			var md = MessageDigest.getInstance("SHA");
			md.update(sb.toString().getBytes());
			// var hex = HexFormat.of();
			Function<Integer, String> f = (b) -> {
				switch(b) {
				case 0xA:
				return "A";
				case 0xB:
				return "B";
				case 0xC:
				return "C";
				case 0xD:
				return "D";
				case 0xE:
				return "E";
				case 0xF:
				return "F";
				default:
				return Integer.valueOf(b).toString();
				}
			};
			for(byte b : md.digest()) {
				// sb.append(hex.toHexDigits(b));
				sb.append(f.apply((b & 0xF0)>>4));
				sb.append(f.apply(b & 0x0F));
		
			}
		} catch(Throwable e) {
			
		}
		
		return sb.toString();
	}
}

