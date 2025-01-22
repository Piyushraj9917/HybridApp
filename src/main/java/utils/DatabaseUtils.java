package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseUtils {
    public static Connection connect;

        public static Connection ConnectToDatabase() throws IOException, SQLException {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Config.properties");
            prop.load(fis);

            String dburl = prop.getProperty("url");
            String dbUsername = prop.getProperty("Username");
            String dbPassword = prop.getProperty("password");

            connect  = DriverManager.getConnection(dburl,dbUsername,dbPassword);
            return connect;
        }

        public static ArrayList ExecuteQuery(String Query) throws SQLException, IOException {
            ArrayList DbResult = new ArrayList();
            Statement stmnt = ConnectToDatabase().createStatement();
            ResultSet Data = stmnt.executeQuery(Query);
            ResultSetMetaData metadata = Data.getMetaData();
            int ColumnCount = metadata.getColumnCount();
            while (Data.next())
            {
                String[] row = new String[ColumnCount];
                for(int i=1;i<=ColumnCount;i++)
                {
                    Data.getString(i);
                    row[i-1] = Data.getString(i);
                }

                DbResult.add(row);
            }
            return DbResult;
        }

    public static void closeConnection() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
