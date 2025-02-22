package jp.co.app.web;

import java.util.Map;
import java.util.HashMap;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebQuery {
	private Map<String, String> params;
	private WebQuery() {
		params = new HashMap<>();
	}
	public void add(String key, String value) {
		params.put(key, value);
	}
	public void remove(String key) {
		params.remove(key);
	}
	public String toQuery() {
		return params.entrySet().stream().map(kv->{
				return encode(kv.getKey()) + "=" + encode(kv.getValue());
			}).reduce((p, c) -> {
					return p + "&" + c;
				}).orElse("");
	}

	private String encode(String s) {
		return URLEncoder.encode(s, StandardCharsets.UTF_8);
	}
	
	public static WebQuery of() {
		return new WebQuery();
	}
}
