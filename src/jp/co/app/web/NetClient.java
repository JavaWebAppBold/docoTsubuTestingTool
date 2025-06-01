package jp.co.app.web;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.net.CookieManager;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;

import jp.co.app.commons.Url;
import jp.co.app.results.web.NetResult;

public class NetClient {
	private Url domain;
	private CookieManager cm;
	private HttpClient client;
	private List<NetResult> results;
	private NetResult currentResult;

	private NetClient(Url domain) {
		this.clear();
		this.domain = domain;
	}

	public void clear() {
		this.cm = new CookieManager();
		this.client = HttpClient.newBuilder().cookieHandler(this.cm).build();
		this.clearResult();
	}

	public void clearResult() {
		this.results = new ArrayList<>();
		this.currentResult = null;
	}

	public NetResult getCurrentResult() {
		return this.currentResult;
	}

	public List<NetResult> getResults() {
		return this.results;
	}

	public void get(String path) {
		this.domain.addPath(path);

		var uri = URI.create(this.domain.toString());
		var request = WebRequestBuilder.get(uri);
		NetResult result = null;
		try {
			var response = this.send(request);
			result = NetResult.of(request, response.statusCode(), response.body());
		} catch (Exception e) {
			result = NetResult.of(request, 0, "");
			result.setErrorMessage(e.toString());
		}
		this.currentResult = result;
		this.results.add(result);

		this.domain.removePath(path);
	}

	public void post(String path) {
		this.domain.addPath(path);

		var uri = URI.create(this.domain.toString());
		var request = WebRequestBuilder.post(uri);
		NetResult result = null;
		try {
			var response = this.send(request);
			result = NetResult.of(request, response.statusCode(), response.body());
		} catch (Exception e) {
			result = NetResult.of(request, 0, "");
			result.setErrorMessage(e.toString());
		}
		this.currentResult = result;
		this.results.add(result);

		this.domain.removePath(path);
	}

	public void post(String path, WebQuery param) {
		this.domain.addPath(path);

		var uri = URI.create(this.domain.toString());
		// 何故か直書きじゃないと動かない。
		// var request = WebRequestBuilder.post(uri, param);
		var request = HttpRequest.newBuilder(uri).header("Content-Type", "application/x-www-form-urlencoded")
				.POST(HttpRequest.BodyPublishers.ofString(param.toQuery())).build();
		NetResult result = null;
		try {
			var response = this.send(request);
			result = NetResult.of(request, response.statusCode(), response.body());
			if (400 <= result.getHttpStatus()) {
				result.setErrorMessage(response.body());
			}
		} catch (Exception e) {
			result = NetResult.of(request, 0, "");
			result.setErrorMessage(e.toString());
		}
		this.currentResult = result;
		this.results.add(result);

		this.domain.removePath(path);
	}

	private HttpResponse<String> send(HttpRequest request) throws IOException, InterruptedException {
		return this.client.send(request, BodyHandlers.ofString());
	}

	private String createStackTrace(Exception e) {
		return Stream.of(e.getStackTrace()).map(s -> s.toString()).reduce((p, c) -> p + "\n" + c)
				.orElse("fail creat StackTrace");
	}

	public static NetClient of(Url domain) {
		return new NetClient(domain);
	}
}
