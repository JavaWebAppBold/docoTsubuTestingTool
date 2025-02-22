package jp.co.app.results.web;

import java.net.http.HttpRequest;

public class NetResult {
	private HttpRequest request;
	private int httpStatus;
	private String body;
	private String errorMessage = "";
	
	private NetResult(HttpRequest request, int httpStatus, String body) {
		this.request = request;
		this.httpStatus = httpStatus;
		this.body = body;
	}

	public HttpRequest getRequest() {
		return this.request;
	}
	public int getHttpStatus() {
		return this.httpStatus;
	}
	public String getBody() {
		return this.body;
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	
	public static NetResult of(HttpRequest request, int httpStatus, String body) {
		return new NetResult(request, httpStatus, body);
	}
}
