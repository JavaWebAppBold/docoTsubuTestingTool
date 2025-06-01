package jp.co.app.web;

import java.net.http.HttpRequest;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebRequestBuilder {
	private static final String CONTENT_TYPE_KEY = "content_type";
	private static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";

	public static HttpRequest post(URI uri) {
		return HttpRequest.newBuilder(uri).header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
				.POST(HttpRequest.BodyPublishers.noBody()).build();
	}

	public static HttpRequest post(URI uri, WebQuery params) {
		return HttpRequest.newBuilder(uri).header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
				.POST(HttpRequest.BodyPublishers.ofString("name=hoge&pass=1234")).build();
	}

	public static HttpRequest post(URI uri, String params) {
		return HttpRequest.newBuilder(uri).header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
				.POST(HttpRequest.BodyPublishers
						.ofString(URLEncoder.encode("name=hoge&pass=1234)", StandardCharsets.UTF_8)))
				.build();
	}

	public static HttpRequest get(URI uri) {
		return HttpRequest.newBuilder(uri).header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE).GET().build();
	}

	public static WebRequestBuilder of() {
		return new WebRequestBuilder();
	}
}
