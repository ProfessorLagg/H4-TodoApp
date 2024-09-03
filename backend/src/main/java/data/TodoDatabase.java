package data;

// https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html
// https://www.baeldung.com/java-jdbc
// https://blog.devart.com/connect-to-sql-server-in-java.html

import logging.ConsoleLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TodoDatabase {
		public enum DatabaseType {
				SqlServer,
		}

		public class DatabaseConnectionInfo {
				private static final ArrayList<String> REGISTERED_DRIVERS = new ArrayList<String>();

				public final DatabaseType databaseType;
				private final String username;
				private final String password;
				private final String connectionUrl;

				public DatabaseConnectionInfo(DatabaseType databaseType, String connectionUrl, String username, String password) {
						this.databaseType = databaseType;
						this.connectionUrl = connectionUrl;
						this.username = username;
						this.password = password;
				}

				public Optional<Connection> getConnection() throws SQLException {
						// Get Driver class name
						String driverClassName = "";
						switch (this.databaseType) {
								case SqlServer -> driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
								default ->
												throw new RuntimeException("Could not find driver class for database type: " + this.databaseType);
						}

						// Load driver class
						try {
								if (!REGISTERED_DRIVERS.contains(driverClassName)) {
										Class.forName(driverClassName);
										REGISTERED_DRIVERS.add(driverClassName);
										ConsoleLogger.infoGBL(this.getClass().getName(), "loaded driver " + driverClassName);
								} else {
										ConsoleLogger.debugGBL(this.getClass().getName(), "driver " + driverClassName + " already loaded");
										return Optional.empty();
								}

						} catch (Exception e) {
								ConsoleLogger.errorGBL(this.getClass().getName(), "Could not load driver " + driverClassName, e);
						}

						// Return connection
						Connection connection = DriverManager.getConnection(this.connectionUrl, this.username, this.password);
						return Optional.of(connection);
				}
				// TODO saveToFile

				// TODO loadFromFile
		}

		public final DatabaseConnectionInfo connectionInfo;


		private TodoDatabase(DatabaseType databaseType, String connectionUrl, String username, String password) {
				this.connectionInfo = new DatabaseConnectionInfo(databaseType, connectionUrl, username, password);
		}

		public TodoDatabase sqlserverInstance(String serverName, String instanceName, int port, Map<String, String> properties, String username, String password) {
				String connectionUrl = "jdbc:sqlserver://";
				connectionUrl += serverName + '\\' + instanceName + ':' + port;
				for (String k : properties.keySet()) {
						connectionUrl += ';' + k + '=' + properties.get(k);
				}


				TodoDatabase result = new TodoDatabase(DatabaseType.SqlServer, connectionUrl, username, password);

				return result;
		}


}
