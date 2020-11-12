import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class changepass {
    private byte[]  SecPass1;
    private  byte [] salt1 = new byte[8];
    public changepass(int userId) {

        JFrame frameCpass = new JFrame("Change Password");
        frameCpass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCpass.setSize(300, 300);


        JPasswordField casutaOldPass;
        casutaOldPass = new JPasswordField();
        casutaOldPass.setBounds(75, 45, 150, 25);
        frameCpass.add(casutaOldPass);



        JPasswordField casutaPass;
        casutaPass = new JPasswordField();
        casutaPass.setBounds(75, 115, 150, 25);
        frameCpass.add(casutaPass);


        JPasswordField casutaPass2;
        casutaPass2 = new JPasswordField();
        casutaPass2.setBounds(75, 165, 150, 25);
        frameCpass.add(casutaPass2);

        JLabel etichOldPass;
        etichOldPass = new JLabel();
        etichOldPass.setText("Old Password");
        etichOldPass.setBounds(75, 20, 100, 25);
        etichOldPass.setVisible(true);
        frameCpass.add(etichOldPass);

        JLabel etichPass;
        etichPass = new JLabel();
        etichPass.setText("Password");
        etichPass.setBounds(75, 90, 100, 25);
        etichPass.setVisible(true);
        frameCpass.add(etichPass);

        JLabel etichPass2;
        etichPass2 = new JLabel();
        etichPass2.setText("Password again");
        etichPass2.setBounds(75, 140, 100, 25);
        etichPass2.setVisible(true);
        frameCpass.add(etichPass2);


        JButton buttonCpass = new JButton("OK");
        buttonCpass.setBounds(75, 200, 150, 25);
        frameCpass.add(buttonCpass);

        frameCpass.setLayout(null);
        frameCpass.setLocationRelativeTo(null);

        frameCpass.setVisible(true);

        buttonCpass.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validate(userId, casutaOldPass.getPassword(),casutaPass.getPassword(),casutaPass2.getPassword());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });


    }




    public void validate(int userId, char[] oldpass, char[] newpass, char[] newpass2) throws SQLException {
        boolean loginOk = true;

        Userdb checkUser = new Userdb();


        String username = checkUser.getusername(userId);
        byte[] parolaDb = checkUser.authp(username);
        byte [] salt = checkUser.auths(username);

        /*if (parolaDb == null || salt == null){
            JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password2.");
            return;}*/
        try {
            loginOk = PassEncrypt.authenticate(oldpass,parolaDb,salt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginOk) {

            if (newpass.length < 1){
                loginOk = false;
            }
            if (newpass.length == newpass2.length)
            {
                for (int i = 0; i< newpass.length; i++){
                    if (newpass[i] != newpass[i]){
                        loginOk = false;
                    }
                }
            }

            System.out.println("Bag in db");


            PassEncrypt secPass1 = new PassEncrypt();

            try {
                salt1 = secPass1.generateSalt();
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }


            try {
                SecPass1 = secPass1.getEncryptedPassword(newpass, salt1);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            } catch (InvalidKeySpecException invalidKeySpecException) {
                invalidKeySpecException.printStackTrace();
            }

            Userdb chUser = new Userdb();
            chUser.updatePass(SecPass1, salt1, userId);


        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password.");
        }
    }


}
