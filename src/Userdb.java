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


    public void insert(int id, String username, byte[] pass, byte[] salt, String name, int phone, String email, String address, boolean admin) throws SQLException {
        String sql = "INSERT INTO User(id,username,pass,salt,name,phone,email,address,admin) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            pstmt.setBytes(3, pass);
            pstmt.setBytes(4, salt);
            pstmt.setString(5, name);
            pstmt.setInt(6, phone);
            pstmt.setString(7, email);
            pstmt.setString(8, address);
            pstmt.setBoolean(9, admin);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int lastID() throws SQLException {
        Connection conn = this.conecteaza();
        String sql = "select id from User";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        do {

            lastID = rs.getInt("id");
        } while (rs.next());


        conn.close();
        return lastID;
    }


    public byte[] authp(String username) throws SQLException {
        String sql = "SELECT pass FROM User  WHERE username = ?";
        byte[] blob = null;
        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
         ResultSet rs = pstmt.executeQuery();
        if (!rs.next() ){
            return blob;
        }
       blob = rs.getBytes("pass");

                     conn.close();
        return blob ;

}

    public byte[] auths(String username) throws SQLException {
        String sql = "SELECT salt FROM User  WHERE username = ?";
        byte[] blob1 = null;
        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next() ){
            return blob1;
        }
         blob1 = rs.getBytes("salt");

        conn.close();
        return blob1 ;

    }




























}