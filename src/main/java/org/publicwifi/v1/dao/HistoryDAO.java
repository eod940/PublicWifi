package org.publicwifi.v1.dao;

import org.publicwifi.v1.dto.HistoryDTO;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDAO {
    private static final String dbUrl = "jdbc:sqlite:publicWifi.sqlite3";

    public void createHistory() throws ClassNotFoundException, SQLException {
        Connection connection = null;
//        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
//            String sql = "CREATE TABLE history (? INTEGER PRIMARY KEY, ? REAL, ? REAL, ? TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            Statement statement = connection.createStatement();
//            psmt = connection.prepareStatement(sql);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            connection.setAutoCommit(false);

            statement.executeUpdate("drop table if exists history");
            statement.executeUpdate("CREATE TABLE history (history_id INTEGER PRIMARY KEY, LAT REAL, LNT REAL, searched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

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

    public void dropHistory() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            connection.setAutoCommit(false);

            statement.executeUpdate("DROP TABLE IF EXISTS history");

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

    public void insertHistory(HistoryDTO dto) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
            String sql = "INSERT INTO history(LNT, LAT) values(?, ?)";

            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.

            psmt.setDouble(1, dto.getLNT());
            psmt.setDouble(2, dto.getLAT());

            psmt.executeUpdate();

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
        return;
    }

    public void deleteHistory(int id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            String sql = "DELETE FROM history WHERE history_id = ?";
            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.
            connection.setAutoCommit(false);

//            statement.executeUpdate("delete from history");
            psmt.setInt(1, id);
            psmt.executeUpdate();

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

    public ArrayList<HistoryDTO> selectHistory() throws ClassNotFoundException, SQLException {
        ArrayList<HistoryDTO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            String sql = "select * from history ORDER BY searched_at DESC";
            psmt = connection.prepareStatement(sql);

            psmt.setQueryTimeout(30);  // set timeout to 30 sec.
            connection.setAutoCommit(false);

//            psmt.executeUpdate();
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                HistoryDTO historyDTO = new HistoryDTO(
                        rs.getInt("history_id"),
                        rs.getDouble("LAT"),
                        rs.getDouble("LNT"),
                        rs.getString("searched_at")
                );
                list.add(historyDTO);
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
        return list;
    }
}
