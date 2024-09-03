package logging;

public class NullLogger implements ILogConsumer {
		@Override
		public void write(LogLevel level, String tag, String msg) { }

		@Override
		public void debug(String tag, String msg) { }

		@Override
		public void info(String tag, String msg) { }

		@Override
		public void warning(String tag, String msg) { }

		@Override
		public void error(String tag, String msg) { }

		@Override
		public void error(String tag, String msg, Exception e) { }

		@Override
		public void panic(String tag, String msg) { }
}
