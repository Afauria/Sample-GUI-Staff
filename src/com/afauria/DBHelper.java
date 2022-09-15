package com.afauria;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	private static Connection conn;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_staff?serverTimezone=Asia/Shanghai&characterEncoding=utf8", "root", "root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return conn;
	}
}
