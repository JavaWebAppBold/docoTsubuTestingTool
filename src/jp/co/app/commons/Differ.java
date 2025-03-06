package jp.co.app.commons;

import static jp.co.app.results.DiffResultStatus.SAME;
import static jp.co.app.results.DiffResultStatus.DIFFERENCE;

import jp.co.app.results.DiffResultStatus;

public class Differ<T> {
	private T a = null;
	private T b = null;
	
	private Differ(T _a, T _b) {
		this.a = _a;
		this.b = _b;
	}

	public DiffResultStatus diff() {
		return this.a.equals(this.b) ? SAME : DIFFERENCE;
	}
}
