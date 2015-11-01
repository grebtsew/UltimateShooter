package Editor;

import java.sql.*;

import Enums.Balls;
import Maps.Map;

public class DataBaseHandler {

	int mapnumber = 0;
	Connection c;
	Statement stmt;

	public DataBaseHandler() {
		Map map = new Map(10, 10);
		map.addball(5, 5, Balls.NORMAL);
		CreateDB();
		SaveMap(map);
		System.out.println(OpenMap());
	}

	private void CreateDB() {
		// create db
		c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		// create table
		c = null;
		stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sql = "CREATE TABLE MAPS "
					+ "((ID INT PRIMARY KEY     NOT NULL,"
					+ "NUMBER           INT    NOT NULL, "
					+ " MAP            CHAR(100)     NOT NULL )";
			/*
			 * String sql = "CREATE TABLE COMPANY " +
			 * "(ID INT PRIMARY KEY     NOT NULL," +
			 * " NAME           TEXT    NOT NULL, " +
			 * " AGE            INT     NOT NULL, " +
			 * " ADDRESS        CHAR(50), " + " SALARY         REAL)";
			 */

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");

	}

	public void SaveMap(Map map) {
		// insert
		c = null;
		stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sql = "INSERT INTO MAPS (NUMBER,ROW1,ROW2,ROW3,ROW4,ROW5,ROW6,ROW7,ROW8,ROW9,ROW10) "
					+ "VALUES(" + mapnumber + ", ";

			for (int i = 0; i < map.y; i++) {
				for (int j = 0; j < map.x; j++) {

					sql += map.getBall(j, i);
				}
				if (i == map.y) {
					sql += ");";
				} else {
					sql += ", ";
				}
			}

			/*
			 * String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
			 * + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			 */

			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	private Map OpenMap() {

		// open/select/get
		c = null;
		stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM MAPS;");
			while (rs.next()) {

			}

			/*
			 * ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;"); while
			 * (rs.next()) { int id = rs.getInt("id"); String name =
			 * rs.getString("name"); int age = rs.getInt("age"); String address
			 * = rs.getString("address"); float salary = rs.getFloat("salary");
			 * System.out.println("ID = " + id); System.out.println("NAME = " +
			 * name); System.out.println("AGE = " + age);
			 * System.out.println("ADDRESS = " + address);
			 * System.out.println("SALARY = " + salary); System.out.println(); }
			 */

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return null;
	}

}
