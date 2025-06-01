package jp.co.app.commons;

import java.util.Map;
import java.util.HashMap;

public final class Properties {
	private static Properties instance = null;

	private Map<PropertyKey, Object> configs;

	private Properties() {
		this.configs = new HashMap<>();
	}

	public void add(PropertyKey key, Object value) {
		this.configs.put(key, value);
	}

	public void remove(PropertyKey key) {
		this.configs.remove(key);
	}

	public Object get(PropertyKey key) {
		return this.configs.get(key);
	}

	public static Properties of() {
		if (instance == null) {
			instance = new Properties();
		}
		return instance;
	}
}
