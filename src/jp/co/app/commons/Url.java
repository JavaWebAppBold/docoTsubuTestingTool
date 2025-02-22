package jp.co.app.commons;

import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

public class Url {
	private String protocol;
	private String domain;
	private String port;
	private List<String> paths;
	private Url(String protocol, String domain) {
		this.protocol = protocol;
		this.domain = domain;
		this.port = "";
		this.paths = new ArrayList<>();
	}
	public Url(String protocol, String domain, String port) {
		this.protocol = protocol;
		this.domain = domain;
		this.port = port;
		this.paths = new ArrayList<>();
	}

	public void addPath(String path) {
		this.paths.add(path);
	}
	public void removePath(String path) {
		this.paths.remove(path);
	}
	public void clear() {
		this.paths = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.protocol);
		sb.append("://");
		sb.append(this.domain);
		// TODO もうチョット厳格な検証ロジックにする
		if (!"".equals(this.port)) {
			sb.append(":");
			sb.append(this.port);
		}
		this.paths.stream().forEach(p->{
				sb.append("/");
				sb.append(p);
			});
		return sb.toString();
	}

	public static Url of(String protocol, String domain) {
		return new Url(protocol, domain);
	}
	public static Url of(String protocol, String domain, String port) {
		return new Url(protocol, domain, port);
	}
}
