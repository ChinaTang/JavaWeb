package aboutdb;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BookDB {

    private static final String dbUrl = "jdbc:mariadb://localhost:3306/BookDb";

    private static final String dbUser="root";
    private String dbPwd = "1234";

    private static final String dbClassName = "org.mariadb.jdbc.Driver";

    private Context context;
    private DataSource dataSource;

    public BookDB() throws NamingException, ClassNotFoundException {
//        Class.forName(dbClassName);
        context = new InitialContext();
        dataSource = (DataSource) context.lookup("java:comp/env/jdbc/BookDb");
    }

    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        return dataSource.getConnection();
    }

    public void closeConnection(Connection con){
            try {
                if(con != null)
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void closePrepStmt(PreparedStatement preStmt){
        try {
            if(preStmt != null)
            preStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResultSet(ResultSet rs){
        try {
            if(rs != null)
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfBooks() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int count = 0;

        try {
            connection = getConnection();
            String selectStatement = "select count(*)" + "from BOOKS";
            preparedStatement = connection.prepareStatement(selectStatement);
            rs = preparedStatement.executeQuery();
            if(rs.next())
                count = rs.getInt(1);
        }finally {
            closeResultSet(rs);
            closePrepStmt(preparedStatement);
            closeConnection(connection);
        }

        return count;
    }

    public ArrayList<BookDetails> getBooks() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<BookDetails> books = new ArrayList();
        try{
            connection = getConnection();
            String selectSQL = "select * " + "from BOOKS";
            preparedStatement = connection.prepareStatement(selectSQL);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                BookDetails bookDetails = new BookDetails(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4), rs.getInt(5),
                        rs.getString(6), rs.getInt(7));
                books.add(bookDetails);
            }
        }finally {
            closeResultSet(rs);
            closePrepStmt(preparedStatement);
            closeConnection(connection);
        }
        return books;
    }

    public BookDetails getBookDetail(String bookId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try{
            connection = getConnection();
            String selectSQL = "select * " + "from BOOKS where ID = ? ";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, bookId);
            rs = preparedStatement.executeQuery();

            if(rs.next()){
                BookDetails bookDetails = new BookDetails(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4), rs.getInt(5),
                        rs.getString(6), rs.getInt(7));
                preparedStatement.close();
                return bookDetails;
            }else{
                return null;
            }
        }finally {
            closeResultSet(rs);
            closePrepStmt(preparedStatement);
            closeConnection(connection);
        }

    }


}
