package com.emaster.common.constant;

public class EmasterURL {
	private static final String dataQueryBaseUrl = "http://localhost:8085/dataquery";

	private EmasterURL() {
		// Prevent create this
	}

	public static class DataQuery {
		private DataQuery() {
			// Prevent create this
		}

		public static final String ID = "id";
		public static final String EMAIL = "email";

		public static enum CATEGORY {
			GET_ALL("?page={page}&size={size}"), GET_BY_ID("/{id}"), CREATE(""), UPDATE(""), DELETE("/{id}");

			private String url;

			private CATEGORY(String url) {
				this.url = dataQueryBaseUrl.concat("/categories").concat(url);
			}

			public String build() {
				return url;
			}
		}

		public static enum USER {
			GET_ALL("?page={page}&size={size}"), GET_BY_EMAIL("/{email}"), CREATE(""), UPDATE(""), DELETE("/{id}");

			private String url;

			private USER(String url) {
				this.url = dataQueryBaseUrl.concat("/users").concat(url);
			}

			public String build() {
				return url;
			}
		}

		public static enum STATEMENT {
			GET_ALL("?page={page}&size={size}"), GET_BY_ID("/{id}"), CREATE(""), UPDATE(""), DELETE("/{id}");

			private String url;

			private STATEMENT(String url) {
				this.url = dataQueryBaseUrl.concat("/statements").concat(url);
			}

			public String build() {
				return url;
			}
		}

		public static enum COMMENT {
			GET_ALL("?page={page}&size={size}"), GET_BY_ID("/{id}"), CREATE(""), UPDATE(""), DELETE("/{id}");
			private String url;

			private COMMENT(String url) {
				this.url = dataQueryBaseUrl.concat("/comments").concat(url);
			}

			public String build() {
				return url;
			}

		}
	}

}
