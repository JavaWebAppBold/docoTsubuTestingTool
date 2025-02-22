package jp.co.app.parameters.commands;

import jp.co.app.web.NetClient;

public class NetCommandParameter extends CommandParameter {
	protected NetClient client;
	public NetClient getClient() {
		return this.client;
	}
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		private NetCommandParameter params;
		public Builder() {
			params = new NetCommandParameter();
		}

		public Builder client(NetClient client) {
			params.client = client;
			return this;
		}

		public NetCommandParameter build() {
			return params;
		}
	}
}
