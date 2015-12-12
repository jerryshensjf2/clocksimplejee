package test.javaforever.clocksimplejee4.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import com.javaforever.clocksimplejee4.database.DBConf;

public class TestDBConf{
    private static String databaseURL = "localhost";
    private static String databaseUName = "root";
    private static String databasePWord = "";
    private static String databaseName = "Clock_test";
    private static Connection connection;

    public static String getDatabaseURL(){
        return databaseURL;
    }

    public static String  getDatabaseUName(){
        return databaseUName;
    }

    public static String  getDatabasePWord(){
        return databasePWord;
    }

    public static String  getDatabaseName(){
        return databaseName;
    }
    
    public static Connection initDB() throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/"+TestDBConf.getDatabaseName()+"?user="+TestDBConf.getDatabaseUName()+"&password="+TestDBConf.getDatabasePWord();
            connection = DriverManager.getConnection(url);
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }
        return connection;
    }
}
