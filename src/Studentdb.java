
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Studentdb {
        public int lastID;

        private Connection conecteaza() {
            // SQLite connection string
            String url = "jdbc:sqlite:res/student.db";
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
        String sql = "select id from Student";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        do {

            lastID = rs.getInt("id");
        } while (rs.next());


        conn.close();
        return lastID;
    }

        public void insert(int id, String name, int phone, String email, String address) throws SQLException {
            String sql = "INSERT INTO Student (id,name,phone,email,address) VALUES(?,?,?,?,?)";
            try (Connection conn = this.conecteaza();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                                pstmt.setString(2, name);
                pstmt.setInt(3, phone);
                pstmt.setString(4, email);
                pstmt.setString(5, address);


                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }




    public boolean authp(String name) throws SQLException {
        String sql = "SELECT phone FROM Student  WHERE name = ?";

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


   /* public  void updateS( String date, int id )
    {
        String sql = "UPDATE Student  SET bookid1=?, date1 = ?   WHERE id = ?";

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {



            pstmt.setString(1, student);

            pstmt.setString(2, date);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }*/



    public List<StudLoanedBooks> studBooksList = new ArrayList<>();
    public List<StudLoanedBooks> readStudBooks(int id) throws SQLException {
        String sql = "SELECT bookid1,date1,bookid2,date2,bookid3,date3,bookid4,date4,bookid5,date5 FROM Student  WHERE id = ?";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);


        ResultSet rs = pstmt.executeQuery();

        studBooksList.add(new StudLoanedBooks(

                rs.getInt("bookid1"),
                rs.getString("date1"),
                rs.getInt("bookid2"),
                rs.getString("date2"),
                rs.getInt("bookid3"),
                rs.getString("date3"),
                rs.getInt("bookid4"),
                rs.getString("date4"),
                rs.getInt("bookid5"),
                rs.getString("date5")


                )      );

        conn.close();
        return studBooksList;

    }



    public List<userData> userList = new ArrayList<>();

    public List<userData> getuserList() throws SQLException {
        String sql = "SELECT id, name FROM Student ";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        //  pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        do {

            userList.add(new userData(
                    rs.getInt("id"),
                    rs.getString("name"))

            );
        } while (rs.next());


        conn.close();
        return userList ;

    }




    public  void updateS(int bookid1, String date1, int bookid2, String date2,int bookid3, String date3,int bookid4, String date4,int bookid5, String date5, int id )
    {
        String sql = "UPDATE Student  SET bookid1 = ?, date1 = ?,bookid2 = ?, date2 = ?,bookid3 = ?, date3 = ?,bookid4 = ?, date4 = ?,bookid5 = ?, date5 = ?   WHERE id = ?";

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookid1);
            pstmt.setString(2, date1);
            pstmt.setInt(3, bookid2);
            pstmt.setString(4, date2);
            pstmt.setInt(5, bookid3);
            pstmt.setString(6, date3);
            pstmt.setInt(7, bookid4);
            pstmt.setString(8, date4);
            pstmt.setInt(9, bookid5);
            pstmt.setString(10, date5);
            pstmt.setInt(11, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }











    }
