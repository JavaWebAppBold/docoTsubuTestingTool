package jp.co.app.results;

public enum ResultStatus {
	WJ("WAITING"),
	AC("OK"),
	WA("NG"),
	RE("ERROR");

	private String label;

	private ResultStatus(String label) {
		this.label = label;
	}

	public String getLabel(){
		return label;
	}
	
}

