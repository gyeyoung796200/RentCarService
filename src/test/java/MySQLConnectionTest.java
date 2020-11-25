import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL ="jdbc:mysql://localhost:3306/rentcar?useSSL=false&serverTimezone=UTC";
    private static final String USER="root";
    private static final String PW="1234";


    @Test
    public void testConnection() throws Exception{

        Class.forName(DRIVER);
        try(Connection connection = DriverManager.getConnection(URL,USER,PW)){
            System.out.println(connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
