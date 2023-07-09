package org.publicwifi.v1.dao;

import org.publicwifi.v1.dto.PublicWifiDTO;

import java.sql.*;

public class PublicWifiDAO {
    private static final String dbUrl = "jdbc:sqlite:./publicWifi.sqlite3";

    public void createPublicWifi() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            connection.setAutoCommit(false);

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
            if (connection != null) {
                connection.rollback();
            }
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

    public int insertPublicWifi(PublicWifiDTO dto) throws ClassNotFoundException, SQLException {
        int inserted = 0;
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
            String sql = "insert into public_wifi values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.

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
            psmt.executeUpdate();

            inserted = 1;
            connection.commit();
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            if (connection != null) {
                connection.rollback();
            }
            System.err.println(e.getMessage());
            throw e;
        }
        finally
        {
            try
            {
                if (psmt != null) {
                    psmt.close();
                }
                if(connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return inserted;
    }

    public void deletePublicWifi() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            connection.setAutoCommit(false);

            statement.executeUpdate("delete from public_wifi");

            connection.commit();
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            if (connection != null) {
                connection.rollback();
            }
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null) {
                    connection.close();
                }
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public void selectPublicWifi(int page, double lat, double lnt) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            String sql = "select * from public_wifi " +
                    "order by ABS(LAT - ?) * ABS(LAT - ?) + ABS(LNT - ?) * ABS(LNT - ?) " +
                    "limit 20 offset ? * 20";
            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.
            connection.setAutoCommit(false);

            psmt.setString(1, String.valueOf(lat));
            psmt.setString(2, String.valueOf(lat));
            psmt.setString(3, String.valueOf(lnt));
            psmt.setString(4, String.valueOf(lnt));
            psmt.setString(5, String.valueOf(page));

            ResultSet rs = psmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("X_SWIFI_MGR_NO"));
            }

            connection.commit();
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            if (connection != null) {
                connection.rollback();
            }
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null) {
                    connection.close();
                }
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
