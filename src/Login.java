import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class Login {
    public int userId;
    public boolean isAdmin = false;
    public  Login() {

        JFrame frameLogin = new JFrame("Login");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(300,300);

        JTextField casutaUser;
        casutaUser = new JTextField();
        casutaUser.setBounds(75, 45, 150, 25);
        frameLogin.add(casutaUser);

        JPasswordField casutaPass;
        casutaPass = new JPasswordField();
        casutaPass.setBounds(75, 115, 150, 25);
        frameLogin.add(casutaPass);

        JLabel etichUser;
        etichUser = new JLabel();
        etichUser.setText("Username");
        etichUser.setBounds(75, 20, 100, 25);
        etichUser.setVisible(true);
        frameLogin.add(etichUser);


        JLabel etichPass;
        etichPass = new JLabel();
        etichPass.setText("Password");
        etichPass.setBounds(75, 90, 100, 25);
        etichPass.setVisible(true);
        frameLogin.add(etichPass);



        JButton buttonOk=new JButton("OK");
        buttonOk.setBounds(75,200,150,25);
        frameLogin.add(buttonOk);

        frameLogin.setLayout(null);
        frameLogin.setLocationRelativeTo(null);

        frameLogin.setVisible(true);

        buttonOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validate(casutaUser.getText(),casutaPass.getPassword());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });







    }


    public  void validate(String user, char[] pass) throws SQLException {
        boolean loginOk = true;

        Userdb checkUser = new Userdb();
        byte[] parolaDb = checkUser.authp(user);
        byte [] salt = checkUser.auths(user);

if (parolaDb == null || salt == null){
    JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password2.");
return;}
        try {
            loginOk = PassEncrypt.authenticate(pass,parolaDb,salt);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (loginOk) {
            userId = checkUser.getid(user);
            int isAdminInt = checkUser.getisadmin(user);
            System.out.println(isAdminInt+ user + userId);


            if (isAdminInt == 1) isAdmin = true;
                    else isAdmin = false;

            //System.out.println("Hooray, you did it!");
            new Meniu(userId, isAdmin);

        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password.");
        }
    }



}
