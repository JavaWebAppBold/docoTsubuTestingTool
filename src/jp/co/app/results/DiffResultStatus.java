package jp.co.app.results;

public enum DiffResultStatus {
	SAME("SAME"), DIFFERENCE("DIFFERENCE");

	private String label;

	private DiffResultStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.label;
	}

}
