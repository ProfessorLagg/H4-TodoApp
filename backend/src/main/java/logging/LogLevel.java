package logging;

public enum LogLevel {
		//EMERGENCY("Emergency", 0),
		//ALERT("Alert", 1),
		PANIC("Critical", 2),
		ERROR("Error", 3),
		WARN("Warning", 4),
		//NOTICE("Notice", 5),
		INFO("Informational", 6),
		DEBUG("Debug", 7);

		public final String name;
		public final int value;

		private LogLevel(String name, int value) {
				this.name = name;
				this.value = value;
		}
}
