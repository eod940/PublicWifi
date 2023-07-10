package org.publicwifi.v1.dao;

import org.publicwifi.v1.dto.BookmarkDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookmarkDAO {
    private static final String dbUrl = "jdbc:sqlite:publicWifi.sqlite3";

    public void createBookmark() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            connection.setAutoCommit(false);

            statement.executeUpdate("drop table if exists bookmark");
            statement.executeUpdate("CREATE TABLE bookmark (bookmark_id INTEGER PRIMARY KEY, bookmark_name TEXT, bookmark_num INT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, edited_at TIMESTAMP, marked_wifi_id TEXT)");

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

    public void dropBookmark() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            connection.setAutoCommit(false);

            statement.executeUpdate("DROP TABLE IF EXISTS bookmark");

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

    public void makeBookmark(BookmarkDTO dto) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
            String sql = "insert into bookmark(bookmark_name, bookmark_num) values(?, ?)";

            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.

            psmt.setString(1, dto.getBookmark_name());
            psmt.setInt(2, dto.getBookmark_num());

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
    }

    public void insertWifiToBookmark(BookmarkDTO dto) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
            String sql = "UPDATE bookmark SET marked_wifi_id = ? WHERE bookmark_id = ?";

            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.

            psmt.setString(1, dto.getMarked_wifi_id());
            psmt.setInt(2, dto.getBookmark_id());

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
    }

    public void updateBookmark(BookmarkDTO dto) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            LocalDateTime localDateTime = LocalDateTime.now();
            String editTime = localDateTime.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            );

            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
            String sql = "UPDATE bookmark SET bookmark_name = ?, bookmark_num = ?, edited_at = ? WHERE bookmark_id = ?";

            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.

            psmt.setString(1, dto.getBookmark_name());
            psmt.setInt(2, dto.getBookmark_num());
            psmt.setString(3, editTime);
            psmt.setInt(4, dto.getBookmark_id());

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
    }

    public void deleteBookmark(int id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement psmt = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);

            String sql = "DELETE FROM bookmark WHERE bookmark_id = ?";
            psmt = connection.prepareStatement(sql);
            psmt.setQueryTimeout(30);  // set timeout to 30 sec.

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
                if (psmt != null) {
                    psmt.close();
                }
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

    public ArrayList<BookmarkDTO> selectBookmark() throws ClassNotFoundException, SQLException {
        ArrayList<BookmarkDTO> list = new ArrayList<>();
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");

        try
        {
            // create a database connection
            connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("SELECT * FROM bookmark ORDER BY bookmark_num");
            while (rs.next()) {
                BookmarkDTO bookmarkDTO = new BookmarkDTO(
                        rs.getInt("bookmark_id"),
                        rs.getString("bookmark_name"),
                        rs.getInt("bookmark_num"),
                        rs.getString("created_at"),
                        rs.getString("marked_wifi_id"),
                        rs.getString("edited_at")
                        );
                list.add(bookmarkDTO);
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
