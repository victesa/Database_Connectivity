import com.mysql.cj.x.protobuf.MysqlxResultset;

import java.sql.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Main {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dbBooks";
    static final String USER = "root";
    static final String PASSWORD = "";


    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // Disable auto-commit mode
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tblBooks");

            // Create JTable
            String[] columnNames = {"ISBN", "Author", "Title", "Edition"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(model);

// Populate JTable with data
            while (resultSet.next()) {
                String isbnNo = resultSet.getString("ISBN");
                String author = resultSet.getString("Author");
                String title = resultSet.getString("Title");
                String edition = resultSet.getString("Edition");
                Object[] row = {isbnNo, author, title, edition};
                model.addRow(row);
            }


            resultSet.close();

            // Create JPanel to hold JTable
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            // Create JFrame to hold JPanel
            JFrame frame = new JFrame("Books");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(panel);

            frame.pack();

            frame.setVisible(true);

            // Commit the transaction
            connection.commit();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                if (connection != null) {
                    connection.close();
                }

            }
            finally {
                assert resultSet != null;
                resultSet.close();
            }
        }
    }


}
