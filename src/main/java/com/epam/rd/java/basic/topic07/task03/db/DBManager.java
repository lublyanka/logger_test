package com.epam.rd.java.basic.topic07.task03.db;

import com.epam.rd.java.basic.topic07.task03.CheckedFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static DBManager instance;
    private final PropertiesLoader properties;



    private DBManager() {
        properties = PropertiesLoader.getInstance();

    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        } else return instance;

        return new DBManager();
    }



    private <T> T execute(CheckedFunction<Connection, T> action) throws DBException {
        try (Connection con = DriverManager.getConnection(properties.getProperties().getProperty("connection.url"))) {
            try {
                con.setAutoCommit(false);
                T result = action.apply(con);
                con.commit();
                //con.setAutoCommit(true);
                return result;
            } catch (DBException e) {
                con.rollback();
                throw new DBException(e);
            } catch (Exception e) {
                throw new DBException(e);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}