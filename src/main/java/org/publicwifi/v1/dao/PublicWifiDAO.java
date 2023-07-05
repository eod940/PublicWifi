package org.publicwifi.v1.dao;

import java.sql.*;

public class PublicWifiDAO {
    private static final String dbUrl = "jdbc:sqlite:./publicWifi.sqlite3";

    public void createPublicWifi() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists public_wifi");
            statement.executeUpdate("create table public_wifi " +
                    "(X_SWIFI_MGR_NO TEXT PRIMARY KEY,\n" +
                    "    X_SWIFI_WRDOFC TEXT,\n" +
                    "    X_SWIFI_MAIN_NM TEXT,\n" +
                    "    X_SWIFI_ADRES1 TEXT,\n" +
                    "    X_SWIFI_ADRES2 TEXT,\n" +
                    "    X_SWIFI_INSTL_FLOOR TEXT,\n" +
                    "    X_SWIFI_INSTL_TY TEXT,\n" +
                    "    X_SWIFI_INSTL_MBY TEXT,\n" +
                    "    X_SWIFI_SVC_SE TEXT,\n" +
                    "    X_SWIFI_CMCWR TEXT,\n" +
                    "    X_SWIFI_CNSTC_YEAR TEXT,\n" +
                    "    X_SWIFI_INOUT_DOOR TEXT,\n" +
                    "    X_SWIFI_REMARS3 TEXT,\n" +
                    "    LAT REAL,\n" +
                    "    LNT REAL,\n" +
                    "    WORK_DTTM TEXT)");

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public void insertPublicWifi() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("");
            statement.executeUpdate("");

//            statement.executeUpdate("delete from public_wifi");
            statement.executeUpdate("insert into public_wifi values('leo', 'dfjakdfj', 'df', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 37.4, 123.4, 'm')");
            ResultSet rs = statement.executeQuery("select * from public_wifi");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("X_SWIFI_MGR_NO"));
                System.out.println("id = " + rs.getString("X_SWIFI_WRDOFC"));
                System.out.println("lat = " + rs.getString("LAT"));
                System.out.println("lnt = " + rs.getString("LNT"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
