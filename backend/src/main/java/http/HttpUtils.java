package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class HttpUtils {
		public static String[] ReadLines(InputStream inputStream) throws IOException {
				try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
						try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
								return bufferedReader.lines().toArray(String[]::new);
						}
				}
		}

		public static byte[] concatArray(byte[] array1, byte[] array2) {
				byte[] result = Arrays.copyOf(array1, array1.length + array2.length);
				System.arraycopy(array2, 0, result, array1.length, array2.length);
				return result;
		}

		public static boolean IsNullOrWhiteSpace(String str) { return (str == null) || str.isEmpty() || str.isBlank(); }

		private HttpUtils() { }
}
