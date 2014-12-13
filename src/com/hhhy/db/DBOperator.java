package com.hhhy.db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBOperator {
    private static DataSource pool = new ComboPooledDataSource();
    private static final Logger logger = Logger.getLogger(DBOperator.class);

    static {
        init();
    }

    private static void init() {
        logger.info("init DBOperator...");
        Properties properties = new Properties();
        try {
            properties.load(DBOperator.class.getClassLoader()
                    .getResourceAsStream("c3p0.properties"));
        } catch (IOException e) {
            logger.error("IO exception happened while init DBOperate", e);
        }
        String driverClass = properties.getProperty("c3p0.driverClass",
                "com.mysql.jdbc.Driver");
        String jdbcUrl = properties.getProperty("c3p0.jdbcUrl",
                "jdbc:mysql://10.1.1.26:3306/zhouxuan?characterEncoding=utf-8");
        String user = properties.getProperty("c3p0.user", "zhouxuan");
        String password = properties.getProperty("c3p0.password", "zxzxlbq");
        int initialPoolSize = Integer.parseInt(properties.getProperty(
                "c3p0.initialPoolSize", "3"));
        int minPoolSize = Integer.parseInt(properties.getProperty(
                "c3p0.minPoolSize", "3"));
        int acquireIncrement = Integer.parseInt(properties.getProperty(
                "c3p0.acquireIncrement", "3"));
        int maxPoolSize = Integer.parseInt(properties.getProperty(
                "c3p0.maxPoolSize", "15"));
        int maxIdleTime = Integer.parseInt(properties.getProperty(
                "c3p0.maxIdleTime", "100"));
        int acquireRetryAttempts = Integer.parseInt(properties.getProperty(
                "c3p0.acquireRetryAttempts", "30"));
        int acquireRetryDelay = Integer.parseInt(properties.getProperty(
                "c3p0.acquireRetryDelay", "1000"));
        ComboPooledDataSource pool = (ComboPooledDataSource) DBOperator.pool;
        try {
            pool.setDriverClass(driverClass);
            pool.setJdbcUrl(jdbcUrl);
            pool.setUser(user);
            pool.setPassword(password);
            pool.setInitialPoolSize(initialPoolSize);
            pool.setMinPoolSize(minPoolSize);
            pool.setAcquireIncrement(acquireIncrement);
            pool.setMaxPoolSize(maxPoolSize);
            pool.setMaxIdleTime(maxIdleTime);
            pool.setAcquireRetryAttempts(acquireRetryAttempts);
            pool.setAcquireRetryDelay(acquireRetryDelay);
            logger.info("init DBOperator success...");
        } catch (PropertyVetoException e) {
            logger.error("PropertyVetoException happened while init DBOperate",
                    e);
        }
    }

    public static <T> T select(String sql, ResultSetHandler<T> handler,
            java.lang.Object... params) throws SQLException {
        QueryRunner qr = new QueryRunner(pool);
        return qr.query(sql, handler, params);
    }

    public static List<Object[]> selectArrayList(String sql,
            java.lang.Object... params) throws SQLException {
        QueryRunner qr = new QueryRunner(pool);
        return qr.query(sql, new ArrayListHandler(), params);
    }

    public static boolean update(String sql, java.lang.Object... params)
            throws SQLException {
        QueryRunner qr = new QueryRunner(pool);
        return (qr.update(sql, params) == 1);
    }

    /**
     * 对有自增id数据表的插入，成功返回自增id，失败返回-1
     * 
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
//    public static long insert(String sql, java.lang.Object... params)
//            throws SQLException {
//        QueryRunner qr = new QueryRunner(pool);
//        boolean result = (qr.update(sql, params) == 1);
//
//        if (result == false) {
//            return -1L;
//        } else {
////            QueryRunner qr2 = new QueryRunner(pool);
////            (Long) qr2.query("select LAST_INSERT_ID()", new ScalarHandler());
//            qr.query("select LAST_INSERT_ID()",
//                    new ScalarHandler());
//            return (Long) qr.query("select LAST_INSERT_ID()",
//                    new ScalarHandler());
//        }
//    }

    public static int max(String sql) throws SQLException {
        QueryRunner qr = new QueryRunner(pool);
        return (Integer) qr.query(sql, new ScalarHandler());
    }
    
    public static long maxlong(String sql) throws SQLException {
        QueryRunner qr = new QueryRunner(pool);
        return (Long) qr.query(sql, new ScalarHandler());
    }
}
