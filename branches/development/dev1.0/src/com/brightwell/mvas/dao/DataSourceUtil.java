package com.brightwell.mvas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.brightwell.mvas.service.cropimage.FrameCroppingException;

public final class DataSourceUtil {

	private static Logger logger = Logger.getLogger(DataSourceUtil.class);
	
	private DataSourceUtil() {
		super();
	}

	public static Connection getHSQLConnection() throws FrameCroppingException {
		String driver = "org.hsqldb.jdbcDriver";
		String url = "jdbc:hsqldb:data/tutorial";
		try{
			Class.forName(driver);
			return DriverManager.getConnection(url, "sa", "");
		}catch (SQLException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		} catch (ClassNotFoundException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		}
	}

	public static Connection getMySqlConnection()
			throws FrameCroppingException {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost/demo2s";
		String username = "root";
		String password = "root";
		try{
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		}catch (SQLException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		} catch (ClassNotFoundException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		}
	}

	public static Connection getSQLiteConnection(String db)
			throws FrameCroppingException {
		String driver = "org.sqlite.JDBC";
		String url = "jdbc:sqlite:" + db;
		try{
			Class.forName(driver);
			return DriverManager.getConnection(url);
		}catch (SQLException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		} catch (ClassNotFoundException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		}
	}

	public static Connection getOracleConnection()
			throws FrameCroppingException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:databaseName";
		String username = "userName";
		String password = "password";
		try{
		Class.forName(driver); // load Oracle driver
		return DriverManager.getConnection(url, username, password);
		}catch (SQLException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		} catch (ClassNotFoundException e) {
			logger.error("Sql Exception",e);
			throw new FrameCroppingException("Sql Exception",e);
		}
	}
}
