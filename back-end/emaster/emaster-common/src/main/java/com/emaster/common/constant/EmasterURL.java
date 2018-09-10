package com.emaster.common.constant;

import java.io.File;

public class EmasterURL {
	private static final String DATA_QUERY_BASE_URL = "http://localhost:8085/dataquery";
	
	public static final String ROOT_PATH = System.getProperty("catalina.home");
	public static final String UPLOAD_PATH = ROOT_PATH + File.separator + "temp";
	private static final String PAGING = "?page={page}&size={size}";

	private EmasterURL() {
		// Prevent create this
	}

	public static class DataQuery {
		private DataQuery() {
			// Prevent create this
		}

		public static final String ID = "id";
		public static final String EMAIL = "email";

		public enum CATEGORY {
			GET_ALL(PAGING), GET_BY_ID("/{id}"), CREATE(""), UPDATE(""), DELETE("/{id}");

			private String url;

			private CATEGORY(String url) {
				this.url = DATA_QUERY_BASE_URL.concat("/categories").concat(url);
			}

			public String build() {
				return url;
			}
		}

		public enum USER {
			GET_ALL(PAGING), GET_BY_EMAIL("/{email}"), CREATE(""), UPDATE(""), DELETE("/{id}");

			private String url;

			private USER(String url) {
				this.url = DATA_QUERY_BASE_URL.concat("/users").concat(url);
			}

			public String build() {
				return url;
			}
		}

		public enum STATEMENT {
			GET_ALL(PAGING), GET_BY_ID("/{id}"), CREATE(""), UPDATE(""), DELETE("/{id}"),
			GET_BY_CATEGORY_ID("/category?id={categoryId}&page={page}&size={size}");

			private String url;

			private STATEMENT(String url) {
				this.url = DATA_QUERY_BASE_URL.concat("/statements").concat(url);
			}

			public String build() {
				return url;
			}
		}

		public enum COMMENT {
			GET_ALL(PAGING), GET_BY_ID("/{id}"), CREATE(""), UPDATE(""), DELETE("/{id}");
			private String url;

			private COMMENT(String url) {
				this.url = DATA_QUERY_BASE_URL.concat("/comments").concat(url);
			}

			public String build() {
				return url;
			}
		}
		
		public enum USER_MEMORY {
			GET_MISSING_STATEMENTS("/missingStatments?userId={userId}&categoryId={categoryId}&pointLimit={pointLimit}"),
			ADD_TO_MEMORY("/addToMemory");
			
			private String url;
			
			private USER_MEMORY(String url) {
				this.url = DATA_QUERY_BASE_URL.concat("/userMemory").concat(url);
			}
			
			public String build() {
				return url;
			}
		}
	}

}
