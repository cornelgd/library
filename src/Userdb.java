import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public int getid(String username) throws SQLException {
        String sql = "SELECT id FROM User  WHERE username = ?";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        int id = rs.getInt("id");

        conn.close();
        return id ;

    }

    public int getisadmin(String username) throws SQLException {
        String sql = "SELECT admin FROM User  WHERE username = ?";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        int admin = rs.getInt("admin");
        conn.close();
        return admin ;

    }

    public String getusername(int id) throws SQLException {
        String sql = "SELECT username FROM User  WHERE id = ?";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        String uname = rs.getString("username");
        conn.close();
        return uname ;

    }

    public List<userDataComplete> userDetailsList = new ArrayList<>();
    public List<userDataComplete> readUserDetails(int id) throws SQLException {
        String sql = "SELECT id, username, pass, salt, name,phone, email,address, admin FROM User  WHERE id = ?";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);


        ResultSet rs = pstmt.executeQuery();

        userDetailsList.add(new userDataComplete(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getBytes("pass"),
                rs.getBytes("salt"),
                rs.getString("name"),
                rs.getInt("phone"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getBoolean("admin")
                )      );

        conn.close();
        return userDetailsList ;

    }











    public  void updatePass(byte[] pass,byte[] salt, int id )
    {
        String sql = "UPDATE User  SET pass = ?, salt = ? WHERE id = ?";

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setBytes(1, pass);
            pstmt.setBytes(2, salt);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
         //   System.out.println("aici");
            System.out.println(e.getMessage());
        }
    }

    public  void update(int id, String username, byte[] pass,byte[] salt, String name,int phone, String email,String address, boolean admin )
    {
        String sql = "UPDATE User  SET pass = ?, salt = ? , name = ?, phone = ?, email = ?, address = ?, admin = ?  WHERE id = ?";

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setBytes(1, pass);
            pstmt.setBytes(2, salt);
            pstmt.setString(3, name);
            pstmt.setInt(4, phone);
            pstmt.setString(5, email);
            pstmt.setString(6, address);
            pstmt.setBoolean(7, admin);
            pstmt.setInt(8, id);






            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("aici");
            System.out.println(e.getMessage());
        }
    }

    public List<userData> userList = new ArrayList<>();

    public List<userData> getuserList() throws SQLException {
        String sql = "SELECT id, username FROM User ";

        Connection conn = this.conecteaza();

        PreparedStatement pstmt = conn.prepareStatement(sql);
      //  pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        do {

            userList.add(new userData(
                    rs.getInt("id"),
                    rs.getString("username"))

            );
        } while (rs.next());


        conn.close();
        return userList ;

    }

















}