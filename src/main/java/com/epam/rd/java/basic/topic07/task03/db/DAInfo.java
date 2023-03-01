package com.epam.rd.java.basic.topic07.task03.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAInfo {

    private static final String GET_ALL_TABLES = "SELECT table_name\n" +
            "FROM information_schema.tables\n" +
            "WHERE table_type = 'BASE TABLE'\n" +
            "AND table_schema NOT IN ('pg_catalog', 'information_schema');";

    public static List<String> getTables(Connection connection) throws DBException {
        List<String> tables = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_TABLES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            rs.close();
            return tables;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
