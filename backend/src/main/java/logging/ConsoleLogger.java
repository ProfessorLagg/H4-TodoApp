package logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements ILogConsumer {

		private static String getTimestamp() { return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME); }

		private static String makeLogLine(LogLevel level, String tag, String msg) {
				return getTimestamp() +
								"\t" +
								level +
								"\t" +
								tag +
								"\t" +
								msg;
		}

		@Override
		public void write(LogLevel level, String tag, String msg) { writeGBL(level, tag, msg); }

		@Override
		public void debug(String tag, String msg) { writeGBL(LogLevel.DEBUG, tag, msg); }

		@Override
		public void info(String tag, String msg) { writeGBL(LogLevel.INFO, tag, msg); }

		@Override
		public void warning(String tag, String msg) { writeGBL(LogLevel.WARN, tag, msg); }

		@Override
		public void error(String tag, String msg) { writeGBL(LogLevel.ERROR, tag, msg); }

		@Override
		public void error(String tag, String msg, Exception e) { writeGBL(LogLevel.ERROR, tag, msg + '\n' + LoggingUtils.getStackTrace(e)); }

		@Override
		public void panic(String tag, String msg) { writeGBL(LogLevel.PANIC, tag, msg); }

		public static void writeGBL(LogLevel level, String tag, String msg) { System.out.println(makeLogLine(level, tag, msg)); }

		public static void debugGBL(String tag, String msg) { writeGBL(LogLevel.DEBUG, tag, msg); }

		public static void infoGBL(String tag, String msg) { writeGBL(LogLevel.INFO, tag, msg); }

		public static void warningGBL(String tag, String msg) { writeGBL(LogLevel.WARN, tag, msg); }

		public static void errorGBL(String tag, String msg) { writeGBL(LogLevel.ERROR, tag, msg); }

		public static void errorGBL(String tag, String msg, Exception e) { writeGBL(LogLevel.ERROR, tag, msg + '\n' + LoggingUtils.getStackTrace(e)); }

		public static void panicGBL(String tag, String msg) { writeGBL(LogLevel.PANIC, tag, msg); }
}
