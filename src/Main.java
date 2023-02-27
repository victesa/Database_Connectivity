import java.sql.*;

public class Main {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/dbBooks";
    static final String USER = "root";
    static final String password = "";

    public static void main(String[] args) throws SQLException{
        System.out.println("Hello world!");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tblBooks");

            connection.setAutoCommit(false);

            GraphicalUserInterface myObj = new GraphicalUserInterface();

            int i = 0;
            while (resultSet.next()){

                String author = (String) resultSet.getObject(2);
                String title = (String) resultSet.getObject(3);
                System.out.println(author + " ," + title);
                myObj.text = "The author is " + author + " and the title is " + title;
                myObj.textArea.setText(myObj.text);
                GraphicalUserInterface.addComponent(myObj.textArea);
                myObj.setVisible(true);
                myObj.setSize(400, 400);
                connection.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}