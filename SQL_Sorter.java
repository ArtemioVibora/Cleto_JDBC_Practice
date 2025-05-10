import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//SQL_Sorter is responsible for the SQL magic for APP
//Done on March 30
public class SQL_Sorter {

    static final String url = "jdbc:mysql://localhost:3306/q4";
    static final String user = "root";
    static final String password = "bruh";

    public static Displays display = new Displays();

    //This function checks if ID doesn't exists because in the Insert function there is an if
    //statement. If the condition is true, it would execute the insert row
    public static boolean doesIDNotExist(Connection conn, int id)
    {
        String statement = "SELECT * FROM purchase_history WHERE id_purchase_history=?";
        try
        {
            PreparedStatement statementDoesIDNotExist = conn.prepareStatement(statement);
            statementDoesIDNotExist.setInt(1, id);
            ResultSet resultSet = statementDoesIDNotExist.executeQuery();
            while(resultSet.next())
            {
                if (resultSet.getInt("id_purchase_history") == id)
                {
                    return false;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    //Like the function doesIDNotExist however I reverse the return statement, if id == resultSet.getInt
    //it would return true and it is for the search function
    public static boolean doesIDExist(Connection conn, int id, String statement)
    {
        try
        {
            PreparedStatement statementDoesIDExist = conn.prepareStatement(statement);
            statementDoesIDExist.setInt(1, id);
            ResultSet resultSet = statementDoesIDExist.executeQuery();
            while(resultSet.next())
            {
                if (resultSet.getInt("id_purchase_history") == id)
                {
                    return true;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    //Done on march 28: 11:59 pm
    public void insertItem(int id_purchase_history, String item_name, int quantity, int payment_type, String payment_reference)
    {
        boolean doesIDNotExist;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            doesIDNotExist = doesIDNotExist(conn, id_purchase_history);
            if (doesIDNotExist)
            {
                String sql = "INSERT INTO purchase_history VALUES (?,?,?,?,?)";
                PreparedStatement statementInsertItem = conn.prepareStatement(sql);
                statementInsertItem.setInt(1, id_purchase_history);
                statementInsertItem.setString(2, item_name);
                statementInsertItem.setInt(3, quantity);
                statementInsertItem.setInt(4, payment_type);
                statementInsertItem.setString(5, payment_reference);

                statementInsertItem.execute();

                System.out.println("Item has been inserted!");
            }
            else
            {
                System.err.println("Error! Item Already exists!");
            }
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ce)
        {
            System.err.println(ce.getMessage());
        }
    }

    //Done on March 29: 2:43 pm
    //This function searches the ID
    public void searchItem(int id) {
        String statement = "SELECT * FROM purchase_history WHERE id_purchase_history=?";
        boolean doesIDExist;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            doesIDExist = doesIDExist(conn, id, statement);
            if (doesIDExist)
            {
                PreparedStatement statementSearch = conn.prepareStatement(statement);
                statementSearch.setInt(1, id);
                ResultSet resultSet = statementSearch.executeQuery();
                while (resultSet.next())
                {
                    display.displaySearchedItem(resultSet.getInt("id_purchase_history"),
                            resultSet.getString("item_name"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("payment_type"),
                            resultSet.getString("payment_reference"));
                }
            }
            else
            {
                System.err.println("Error! Item does not exist");
            }

        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ce)
        {
            System.err.println(ce.getMessage());
        }
    }
}
