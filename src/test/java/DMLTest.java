import org.junit.Test;

import java.sql.*;

public class DMLTest {

    @Test
    public void insertData() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("org.sqlite.JDBC");
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/leo/github/Project/PublicWifi/publicWifi.sqlite3");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("delete from public_wifi");
            statement.executeUpdate("insert into public_wifi values(1, 'leo', 'dfjakdfj', 'df', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm')");
            ResultSet rs = statement.executeQuery("select * from public_wifi");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("X_SWIFI_MGR_NO"));
                System.out.println("id = " + rs.getString("X_SWIFI_WRDOFC"));
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
