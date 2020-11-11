import java.sql.*;

public class Userdb {
    public int lastID;

    private Connection conecteaza() {
        // SQLite connection string
        String url = "jdbc:sqlite:res/user.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void insert(int id, String username, String pass, String name, int phone, String email,String address, boolean admin) throws SQLException {
        String sql = "INSERT INTO User(id,username,pass,name,phone,email,address,admin) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            pstmt.setString(3, pass);
            pstmt.setString(4, name);
            pstmt.setInt(5, phone);
            pstmt.setString(6, email);
            pstmt.setString(7, address);
            pstmt.setBoolean(8, admin);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int lastID() throws SQLException {
        Connection conn = this.conecteaza();
        String sql ="select id from User";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        do {

            lastID = rs.getInt("id");
        } while (rs.next());


        conn.close();
        return lastID;
    }











}