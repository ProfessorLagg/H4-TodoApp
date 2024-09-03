package http;

public enum HttpResponseCode {
		CONTINUE("Continue", 100),
		SWITCHING_PROTOCOLS("Switching Protocols", 101),
		PROCESSING("Processing", 102),
		EARLY_HINTS("Early Hints", 103),
		OK("OK", 200),
		CREATED("Created", 201),
		ACCEPTED("Accepted", 202),
		NON_AUTHORITATIVE_INFORMATION("Non-Authoritative Information", 203),
		NO_CONTENT("No Content", 204),
		RESET_CONTENT("Reset Content", 205),
		PARTIAL_CONTENT("Partial Content", 206),
		MULTI_STATUS("Multi-Status", 207),
		ALREADY_REPORTED("Already Reported", 208),
		IM_USED("IM Used", 226),
		MULTIPLE_CHOICES("Multiple Choices", 300),
		MOVED_PERMANENTLY("Moved Permanently", 301),
		FOUND("Found", 302),
		SEE_OTHER("See Other", 303),
		NOT_MODIFIED("Not Modified", 304),
		USE_PROXY("Use Proxy", 305),
		UNUSED("unused", 306),
		TEMPORARY_REDIRECT("Temporary Redirect", 307),
		PERMANENT_REDIRECT("Permanent Redirect", 308),
		BAD_REQUEST("Bad Request", 400),
		UNAUTHORIZED("Unauthorized", 401),
		PAYMENT_REQUIRED("Payment Required", 402),
		FORBIDDEN("Forbidden", 403),
		NOT_FOUND("Not Found", 404),
		METHOD_NOT_ALLOWED("Method Not Allowed", 405),
		NOT_ACCEPTABLE("Not Acceptable", 406),
		PROXY_AUTHENTICATION_REQUIRED("Proxy Authentication Required", 407),
		REQUEST_TIMEOUT("Request Timeout", 408),
		CONFLICT("Conflict", 409),
		GONE("Gone", 410),
		LENGTH_REQUIRED("Length Required", 411),
		PRECONDITION_FAILED("Precondition Failed", 412),
		PAYLOAD_TOO_LARGE("Payload Too Large", 413),
		URI_TOO_LONG("URI Too Long", 414),
		UNSUPPORTED_MEDIA_TYPE("Unsupported Media Type", 415),
		RANGE_NOT_SATISFIABLE("Range Not Satisfiable", 416),
		EXPECTATION_FAILED("Expectation Failed", 417),
		IM_A_TEAPOT("I'm a teapot", 418),
		MISDIRECTED_REQUEST("Misdirected Request", 421),
		UNPROCESSABLE_CONTENT("Unprocessable Content", 422),
		LOCKED("Locked", 423),
		FAILED_DEPENDENCY("Failed Dependency", 424),
		TOO_EARLY("Too Early", 425),
		UPGRADE_REQUIRED("Upgrade Required", 426),
		PRECONDITION_REQUIRED("Precondition Required", 428),
		TOO_MANY_REQUESTS("Too Many Requests", 429),
		REQUEST_HEADER_FIELDS_TOO_LARGE("Request Header Fields Too Large", 431),
		UNAVAILABLE_FOR_LEGAL_REASONS("Unavailable For Legal Reasons", 451),
		INTERNAL_SERVER_ERROR("Internal Server Error", 500),
		NOT_IMPLEMENTED("Not Implemented", 501),
		BAD_GATEWAY("Bad Gateway", 502),
		SERVICE_UNAVAILABLE("Service Unavailable", 503),
		GATEWAY_TIMEOUT("Gateway Timeout", 504),
		HTTP_VERSION_NOT_SUPPORTED("HTTP Version Not Supported", 505),
		VARIANT_ALSO_NEGOTIATES("Variant Also Negotiates", 506),
		INSUFFICIENT_STORAGE("Insufficient Storage", 507),
		LOOP_DETECTED("Loop Detected", 508),
		NOT_EXTENDED("Not Extended", 510),
		NETWORK_AUTHENTICATION_REQUIRED("Network Authentication Required", 511);

		public final String name;
		public final int code;

		private HttpResponseCode(String name, int code) {
				this.name = name;
				this.code = code;
		}

		public static HttpResponseCode FromCode(int code) {
				switch (code) {
						case 100 -> { return HttpResponseCode.CONTINUE; }
						case 101 -> { return HttpResponseCode.SWITCHING_PROTOCOLS; }
						case 102 -> { return HttpResponseCode.PROCESSING; }
						case 103 -> { return HttpResponseCode.EARLY_HINTS; }
						case 200 -> { return HttpResponseCode.OK; }
						case 201 -> { return HttpResponseCode.CREATED; }
						case 202 -> { return HttpResponseCode.ACCEPTED; }
						case 203 -> { return HttpResponseCode.NON_AUTHORITATIVE_INFORMATION; }
						case 204 -> { return HttpResponseCode.NO_CONTENT; }
						case 205 -> { return HttpResponseCode.RESET_CONTENT; }
						case 206 -> { return HttpResponseCode.PARTIAL_CONTENT; }
						case 207 -> { return HttpResponseCode.MULTI_STATUS; }
						case 208 -> { return HttpResponseCode.ALREADY_REPORTED; }
						case 226 -> { return HttpResponseCode.IM_USED; }
						case 300 -> { return HttpResponseCode.MULTIPLE_CHOICES; }
						case 301 -> { return HttpResponseCode.MOVED_PERMANENTLY; }
						case 302 -> { return HttpResponseCode.FOUND; }
						case 303 -> { return HttpResponseCode.SEE_OTHER; }
						case 304 -> { return HttpResponseCode.NOT_MODIFIED; }
						case 305 -> { return HttpResponseCode.USE_PROXY; }
						case 306 -> { return HttpResponseCode.UNUSED; }
						case 307 -> { return HttpResponseCode.TEMPORARY_REDIRECT; }
						case 308 -> { return HttpResponseCode.PERMANENT_REDIRECT; }
						case 400 -> { return HttpResponseCode.BAD_REQUEST; }
						case 401 -> { return HttpResponseCode.UNAUTHORIZED; }
						case 402 -> { return HttpResponseCode.PAYMENT_REQUIRED; }
						case 403 -> { return HttpResponseCode.FORBIDDEN; }
						case 404 -> { return HttpResponseCode.NOT_FOUND; }
						case 405 -> { return HttpResponseCode.METHOD_NOT_ALLOWED; }
						case 406 -> { return HttpResponseCode.NOT_ACCEPTABLE; }
						case 407 -> { return HttpResponseCode.PROXY_AUTHENTICATION_REQUIRED; }
						case 408 -> { return HttpResponseCode.REQUEST_TIMEOUT; }
						case 409 -> { return HttpResponseCode.CONFLICT; }
						case 410 -> { return HttpResponseCode.GONE; }
						case 411 -> { return HttpResponseCode.LENGTH_REQUIRED; }
						case 412 -> { return HttpResponseCode.PRECONDITION_FAILED; }
						case 413 -> { return HttpResponseCode.PAYLOAD_TOO_LARGE; }
						case 414 -> { return HttpResponseCode.URI_TOO_LONG; }
						case 415 -> { return HttpResponseCode.UNSUPPORTED_MEDIA_TYPE; }
						case 416 -> { return HttpResponseCode.RANGE_NOT_SATISFIABLE; }
						case 417 -> { return HttpResponseCode.EXPECTATION_FAILED; }
						case 421 -> { return HttpResponseCode.MISDIRECTED_REQUEST; }
						case 422 -> { return HttpResponseCode.UNPROCESSABLE_CONTENT; }
						case 423 -> { return HttpResponseCode.LOCKED; }
						case 424 -> { return HttpResponseCode.FAILED_DEPENDENCY; }
						case 425 -> { return HttpResponseCode.TOO_EARLY; }
						case 426 -> { return HttpResponseCode.UPGRADE_REQUIRED; }
						case 428 -> { return HttpResponseCode.PRECONDITION_REQUIRED; }
						case 429 -> { return HttpResponseCode.TOO_MANY_REQUESTS; }
						case 431 -> { return HttpResponseCode.REQUEST_HEADER_FIELDS_TOO_LARGE; }
						case 451 -> { return HttpResponseCode.UNAVAILABLE_FOR_LEGAL_REASONS; }
						case 500 -> { return HttpResponseCode.INTERNAL_SERVER_ERROR; }
						case 501 -> { return HttpResponseCode.NOT_IMPLEMENTED; }
						case 502 -> { return HttpResponseCode.BAD_GATEWAY; }
						case 503 -> { return HttpResponseCode.SERVICE_UNAVAILABLE; }
						case 504 -> { return HttpResponseCode.GATEWAY_TIMEOUT; }
						case 505 -> { return HttpResponseCode.HTTP_VERSION_NOT_SUPPORTED; }
						case 506 -> { return HttpResponseCode.VARIANT_ALSO_NEGOTIATES; }
						case 507 -> { return HttpResponseCode.INSUFFICIENT_STORAGE; }
						case 508 -> { return HttpResponseCode.LOOP_DETECTED; }
						case 510 -> { return HttpResponseCode.NOT_EXTENDED; }
						case 511 -> { return HttpResponseCode.NETWORK_AUTHENTICATION_REQUIRED; }

						default -> throw new RuntimeException("Could not find status code: " + code);
				}
		}
}
