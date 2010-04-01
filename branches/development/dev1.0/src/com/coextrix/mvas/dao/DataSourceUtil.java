package com.coextrix.mvas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {

	public static Connection getHSQLConnection() throws ClassNotFoundException,
			SQLException {
		String driver = "org.hsqldb.jdbcDriver";
		String url = "jdbc:hsqldb:data/tutorial";
		Class.forName(driver);
		System.out.println("Driver Loaded.");
		return DriverManager.getConnection(url, "sa", "");
	}

	public static Connection getMySqlConnection()
			throws ClassNotFoundException, SQLException {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost/demo2s";
		String username = "root";
		String password = "root";

		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	public static Connection getSQLiteConnection(String db)
			throws ClassNotFoundException, SQLException {
		String driver = "org.sqlite.JDBC";
		String url = "jdbc:sqlite:" + db;
		Class.forName(driver);
		return DriverManager.getConnection(url);
	}

	public static Connection getOracleConnection()
			throws ClassNotFoundException, SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:databaseName";
		String username = "userName";
		String password = "password";

		Class.forName(driver); // load Oracle driver
		return DriverManager.getConnection(url, username, password);
	}
}
