package com.ruoyi.sql;

import java.sql.*;

public class MSSqlDBUtils{
    private static String user;
    private static String password;
    private static String dbName;

    private static String url;
    private static String driver;
    public static Connection conn;

    static {
        user = "sa";
        password = "sa.123456";
        dbName = "his_db";
        url = "jdbc:sqlserver://101.42.0.25:1433;encrypt=false;username=" + user + ";password=" + password + ";databaseName=" + dbName;
        driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);//加载驱动

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url);
        return conn;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(sql);
        System.out.println("连接成功");
        System.out.println(ps);
        return ps;
    }
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

