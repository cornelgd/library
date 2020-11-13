public class userDataComplete {

    public int id;
    public String username, name, email, address;
    public int phone;
    public boolean admin;
public byte[] pass, salt;


    public userDataComplete(int id,String username,  byte[] pass,   byte[] salt, String name, int phone, String email, String address, boolean admin ){
        this.id = id ;
        this.username = username  ;
        this.pass = pass ;
        this.salt = salt ;
        this.name = name ;
        this.phone = phone ;
        this.email = email ;
        this.address = address ;
        this.admin = admin ;

    }

}
