package logging;

public interface ILogConsumer {
		public void write(LogLevel level, String tag, String msg);

		public void debug(String tag, String msg);

		public void info(String tag, String msg);

		public void warning(String tag, String msg);

		public void error(String tag, String msg);
		public void error(String tag, String msg, Exception e);

		public void panic(String tag, String msg);
}
