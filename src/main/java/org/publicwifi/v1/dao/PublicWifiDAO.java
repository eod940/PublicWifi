package org.publicwifi.v1.dao;

import org.publicwifi.v1.dto.PublicWifiDTO;

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
            PreparedStatement psmt = connection.prepareStatement("insert into public_wifi values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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

    public void insertPublicWifi(PublicWifiDTO dto) throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            PreparedStatement psmt = connection.prepareStatement("insert into public_wifi values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            psmt.setString(1, dto.getX_SWIFI_MGR_NO());
            psmt.setString(2, dto.getX_SWIFI_WRDOFC());
            psmt.setString(3, dto.getX_SWIFI_MAIN_NM());
            psmt.setString(4, dto.getX_SWIFI_ADRES1());
            psmt.setString(5, dto.getX_SWIFI_ADRES2());
            psmt.setString(6, dto.getX_SWIFI_INSTL_FLOOR());
            psmt.setString(7, dto.getX_SWIFI_INSTL_TY());
            psmt.setString(8, dto.getX_SWIFI_INSTL_MBY());
            psmt.setString(9, dto.getX_SWIFI_SVC_SE());
            psmt.setString(10, dto.getX_SWIFI_CMCWR());
            psmt.setString(11, dto.getX_SWIFI_CNSTC_YEAR());
            psmt.setString(12, dto.getX_SWIFI_INOUT_DOOR());
            psmt.setString(13, dto.getX_SWIFI_REMARS3());
            psmt.setDouble(14, dto.getLAT());
            psmt.setDouble(15, dto.getLNT());
            psmt.setString(16, dto.getWORK_DTTM());

            ResultSet rs = statement.executeQuery("select * from public_wifi");
            while(rs.next())
            {
                // read the result set
                System.out.println("id = " + rs.getString("X_SWIFI_MGR_NO"));
                System.out.println("lat = " + rs.getString("LAT"));
                System.out.println("lnt = " + rs.getString("LNT"));
            }
            statement.executeUpdate("delete from public_wifi");
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
