import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Repository {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/library?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "600591";

    private Connection connection;

    public Repository() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error while registering JDBC driver");
        }

        connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
    }

    /*public void create(Book book) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "insert into employees value (null, ?, ?, ?);"
        );
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setBoolean(3, book.getStatus());
        statement.executeUpdate();
    }*/

    public void showB() throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT*FROM book");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            boolean status = resultSet.getBoolean("status");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Status: " + status);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void selectB(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT*FROM book WHERE id = ?;"
        );

        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            boolean status = resultSet.getBoolean("status");
            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + title);
            System.out.println("Specialty: " + author);
            System.out.println("Status: " + status);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void getB(int id) throws IOException, SQLException {

        PreparedStatement statement = connection.prepareStatement(
                "delete from book where id = ?;"
        );

        statement.setInt(1, id);
        statement.execute();
    }

    public void returnB()
            throws IOException, SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into book values(?, ?,?,?);"
        );
        Scanner scan = new Scanner(System.in);
        System.out.println("Write an id: ");
        int id = scan.nextInt();
        System.out.println("Write a title");
        String title = scan.nextLine();
        //scan.nextLine();
        System.out.println("Write an author");
        String author = scan.nextLine();
        System.out.println("Write a status");
        boolean status = scan.nextBoolean();


        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, author);
        preparedStatement.setBoolean(4, status);
        preparedStatement.execute();

        System.out.println("New book is: \n" + id + " " + title + " " + author + " " + status);
    }
}

