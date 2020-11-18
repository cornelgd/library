import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDb {

    public int lastID;

    private Connection conecteaza() {
        // SQLite connection string
        String url = "jdbc:sqlite:res/book.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public int lastID() throws SQLException {
        Connection conn = this.conecteaza();
        String sql = "select id from Books";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        do {

            lastID = rs.getInt("id");
        } while (rs.next());


        conn.close();
        return lastID;
    }

    public void insert(int id, String name,  String author, int  year ) throws SQLException {
        String sql = "INSERT INTO Books (id,name,author ,year ) VALUES(?,?,?,?)";
        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, author);
            pstmt.setInt(4, year);



            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean authp(String name) throws SQLException {
        String sql = "SELECT name FROM Books  WHERE name = ?";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next() ){
            return true;
        }


        conn.close();
        return false ;

    }





    public List<bookData> bookList = new ArrayList<>();

    public List<bookData> getBookList() throws SQLException {
        String sql = "SELECT id, name, author, date FROM Books ";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        //  pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        do {

            bookList.add(new bookData(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("author"),
                    rs.getString("date")
                    )

            );
        } while (rs.next());


        conn.close();
        return bookList;

    }




    public  void updateB(String student, String date, int id )
    {
        String sql = "UPDATE Books  SET student = ?, date = ?   WHERE id = ?";

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student);
            pstmt.setString(2, date);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }








}
