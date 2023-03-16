package com.Qzhou.myssm.basedao;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;
    private Class entityClass;

    public BaseDAO() {
        Type genericType = this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType)genericType).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];

        try {
            this.entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException var5) {
            var5.printStackTrace();
            throw new DAOException("BaseDAO 构造方法出错了，可能的原因是没有指定<>中的类型");
        }
    }

    protected Connection getConn() {
        return ConnUtil.getConn();
    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
    }

    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for(int i = 0; i < params.length; ++i) {
                psmt.setObject(i + 1, params[i]);
            }
        }

    }

    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        this.conn = this.getConn();

        try {
            if (insertFlag) {
                this.psmt = this.conn.prepareStatement(sql, 1);
            } else {
                this.psmt = this.conn.prepareStatement(sql);
            }

            this.setParams(this.psmt, params);
            int count = this.psmt.executeUpdate();
            if (insertFlag) {
                this.rs = this.psmt.getGeneratedKeys();
                if (this.rs.next()) {
                    return Long.valueOf(this.rs.getLong(1)).intValue();
                }
            }

            return 0;
        } catch (SQLException var5) {
            var5.printStackTrace();
            throw new DAOException("BaseDAO executeUpdate出错了");
        }
    }

    private void setValue(Object obj, String property, Object propertyValue) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class clazz = obj.getClass();
        Field field = clazz.getDeclaredField(property);
        if (field != null) {
            String typeName = field.getType().getName();
            if (isMyType(typeName)) {
                Class typeNameClass = Class.forName(typeName);
                Constructor constructor = typeNameClass.getDeclaredConstructor(Integer.class);
                propertyValue = constructor.newInstance(propertyValue);
            }

            field.setAccessible(true);
            field.set(obj, propertyValue);
        }

    }

    private static boolean isNotMyType(String typeName) {
        return "java.lang.Integer".equals(typeName) || "java.lang.String".equals(typeName) || "java.util.Date".equals(typeName) || "java.sql.Date".equals(typeName) || "java.lang.Double".equals(typeName);
    }

    private static boolean isMyType(String typeName) {
        return !isNotMyType(typeName);
    }

    protected Object[] executeComplexQuery(String sql, Object... params) {
        this.conn = this.getConn();

        try {
            this.psmt = this.conn.prepareStatement(sql);
            this.setParams(this.psmt, params);
            this.rs = this.psmt.executeQuery();
            ResultSetMetaData rsmd = this.rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            if (!this.rs.next()) {
                return null;
            } else {
                for(int i = 0; i < columnCount; ++i) {
                    Object columnValue = this.rs.getObject(i + 1);
                    columnValueArr[i] = columnValue;
                }

                return columnValueArr;
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
            throw new DAOException("BaseDAO executeComplexQuery出错了");
        }
    }

    protected T load(String sql, Object... params) {
        this.conn = this.getConn();

        try {
            this.psmt = this.conn.prepareStatement(sql);
            this.setParams(this.psmt, params);
            this.rs = this.psmt.executeQuery();
            ResultSetMetaData rsmd = this.rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (!this.rs.next()) {
                return null;
            } else {
                T entity = (T)this.entityClass.newInstance();

                for(int i = 0; i < columnCount; ++i) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object columnValue = this.rs.getObject(i + 1);
                    this.setValue(entity, columnName, columnValue);
                }

                return entity;
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            throw new DAOException("BaseDAO load出错了");
        }
    }

    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList();
        this.conn = this.getConn();

        try {
            this.psmt = this.conn.prepareStatement(sql);
            this.setParams(this.psmt, params);
            this.rs = this.psmt.executeQuery();
            ResultSetMetaData rsmd = this.rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while(this.rs.next()) {
                T entity = (T)this.entityClass.newInstance();

                for(int i = 0; i < columnCount; ++i) {
                    String columnName = rsmd.getColumnLabel(i + 1);
                    Object columnValue = this.rs.getObject(i + 1);
                    this.setValue(entity, columnName, columnValue);
                }

                list.add(entity);
            }

            return list;
        } catch (Exception var10) {
            var10.printStackTrace();
            throw new DAOException("BaseDAO executeQuery出错了");
        }
    }
}
