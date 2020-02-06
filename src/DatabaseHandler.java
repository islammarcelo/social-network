import java.sql.*;

public class DatabaseHandler {

    private static DatabaseHandler jdbc;

    //Singleton pattern prevents the instantiation from any other class.
    private DatabaseHandler() {  }
    public static DatabaseHandler getInstance() {
        if (jdbc==null)
        {
            jdbc= new DatabaseHandler();
        }
        return jdbc;
    }

    // to get the connection from methods like insert, view etc.
    private static Connection getConnection() throws Exception {
        Connection con=null;
        Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "");
        return con;
    }


    public static boolean insert(String tableName, String[] values) throws Exception {
        Connection conn = getConnection();

        String cmd = "Insert into " + tableName + " values (";

        for(int i = 0; i < values.length; i++)
        {
            cmd += values[i];
            if(i != values.length - 1)
            {
                cmd += ", ";
            }
        }
        cmd += ");";

        Statement stmt = conn.createStatement();
        int r = stmt.executeUpdate(cmd);

        conn.close();

        if (r == 0)
            return false;
        else
            return true;
    }


    public static boolean customInsert(String tableName, String[] columns, String[] values) throws Exception {
        if (columns.length != values.length)
            return false;

        Connection conn = getConnection();

        String cmd = "Insert into " + tableName;

        String columnsS = "(";
        String valuesS = "values (";
        for (int i = 0; i < values.length; i++)
        {
            columnsS += columns[i];
            valuesS += values[i];

            if(i != values.length - 1)
            {
                columnsS += ", ";
                valuesS += ", ";
            }
        }
        columnsS += ") ";
        valuesS += ");";


        Statement stmt = conn.createStatement();
        int r = stmt.executeUpdate(cmd + columnsS + valuesS);

        conn.close();

        if (r == 0)
            return false;
        else
            return true;
    }

    public static ResultSet select(String tableName, String clause) throws Exception {
        Connection conn = getConnection();
        String cmd = "SELECT * FROM " + tableName + " " + clause;

        Statement stmt = conn.createStatement();
        ResultSet data = stmt.executeQuery(cmd);

        conn.close();
        return data;
    }

    public static ResultSet customSelect(String tableName, String[] columns, String clause) throws Exception {
        Connection conn = getConnection();

        String columnclause = "";
        for(int i = 0; i < columns.length; i++)
        {
            columnclause += columns[i];
            if (i != columns.length - 1)
            {
                columnclause += ", ";
            }
        }
        String cmd = "SELECT " + columnclause + " FROM " + tableName + " " + clause;

        Statement stmt = conn.createStatement();
        ResultSet data = stmt.executeQuery(cmd);

        conn.close();
        return data;
    }

    public static boolean update(String tableName, String[] columns, String[] values, String clause) throws Exception {
        if (columns.length != values.length)
            return false;


        Connection conn = getConnection();

        String valueQ = "";
        for(int i = 0; i < values.length; i++)
        {
            valueQ += columns[i] + "=" + values[i];
            if (i != values.length - 1)
            {
                valueQ += ", ";
            }
        }
        String cmd = "UPDATE " + tableName + " SET " + valueQ + " " + clause;

        Statement stmt = conn.createStatement();
        int r = stmt.executeUpdate(cmd);

        conn.close();

        if (r == 0)
            return false;
        else
            return true;
    }

    public static boolean delete(String tableName, String clause) throws Exception {
        Connection conn = getConnection();

        String cmd = "Delete from " + tableName + " " + clause;

        Statement stmt = conn.createStatement();
        int r = stmt.executeUpdate(cmd);

        conn.close();

        if (r == 0)
            return false;
        else
            return true;
    }
}
