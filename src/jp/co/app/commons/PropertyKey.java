package jp.co.app.commons;

public enum PropertyKey {
	USER(String.class),
	TSUBUYAKI(String.class),
	NONE(Object.class);

	private Class<?> key;

	private PropertyKey(Class<?> key) {
		this.key = key;
	}
	public Class<?> getKey(){
		return key;
	}
}
