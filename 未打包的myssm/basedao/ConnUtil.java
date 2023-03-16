package com.Qzhou.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal();
    static Properties properties = new Properties();

    public ConnUtil() {
    }

    private static Connection createConn() {
        try {
            DataSource druidDataSource = DruidDataSourceFactory.createDataSource(properties);
            return druidDataSource.getConnection();
        } catch (SQLException var1) {
            var1.printStackTrace();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public static Connection getConn() {
        Connection conn = (Connection)threadLocal.get();
        if (conn == null) {
            conn = createConn();
            threadLocal.set(conn);
        }

        return (Connection)threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = (Connection)threadLocal.get();
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
                threadLocal.remove();
            }

        }
    }

    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            properties.load(is);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }
}


