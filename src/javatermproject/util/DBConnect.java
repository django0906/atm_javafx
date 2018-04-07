package javatermproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static DBConnect instance;
	private static Connection conn = null;

	// 파일부를 때 딱 한번 호출하고 세션 유지시간 위주로 알아보자.
	private DBConnect() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:ATM.db");
			System.out.println("[o] successfully connected!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static {
		try {
			System.out.println("[0] create DBConnect instance");
			instance = new DBConnect();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("[x] an error occured!");
		}
	}


	public static DBConnect getInstance(){
		return instance;
	}


	public Connection getConn() {
		return conn;
	}

	public void endConnection() {
		try {
			conn.close();
			System.out.println("[o] connection closed!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection restartConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:ATM.db");
			System.out.println("[o] successfully connected!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
