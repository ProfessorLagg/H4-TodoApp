package http;

public class HttpRoute {
		public final HttpMethod method;
		public final String route;

		public HttpRoute(String route, HttpMethod method) {
				this.route = route;
				this.method = method;
		}

		public HttpRoute(String route) {
				this.route = route;
				this.method = HttpMethod.ANY;
		}

		public boolean matches(HttpRoute otherRoute) {
				boolean methodMatches = (this.method == HttpMethod.ANY) || this.method == otherRoute.method;
				// TODO Regex Mathing or something more complex / dynamic
				boolean routeMatches = this.route.equalsIgnoreCase(otherRoute.route);
				return methodMatches && routeMatches;
		}

		@Override
		public String toString() { return this.method.toString() + ' ' + this.route; }
}
