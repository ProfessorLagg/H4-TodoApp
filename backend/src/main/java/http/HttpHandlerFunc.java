package http;

@FunctionalInterface
public interface HttpHandlerFunc {
		HttpResponse Handle(HttpRequest request);
}
