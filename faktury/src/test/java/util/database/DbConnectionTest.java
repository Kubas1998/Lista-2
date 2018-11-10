package util.database;

import com.obsidiam.util.database.SQLiteConnectionFactory;
import org.junit.Test;

import java.sql.Connection;

import static junit.framework.TestCase.assertNotNull;


public class DbConnectionTest {
    @Test
    public void connectToLocal(){
        SQLiteConnectionFactory sqLiteConnectionFactory = new SQLiteConnectionFactory("org.sqlite.JDBC", "jdbc:sqlite:faktury.db");
        Connection connection = sqLiteConnectionFactory.getConnection();
        assertNotNull(connection);
    }
}
