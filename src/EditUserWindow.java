import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class EditUserWindow {
    boolean editOk = true;
    private  byte [] salt = new byte[8];
    private byte[]  SecPass;
    public EditUserWindow(int id) throws SQLException {
        JFrame frameAddUser = new JFrame("Edit user");
        frameAddUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAddUser.setSize(650, 350);

        JTextField casutaUser;
        casutaUser = new JTextField();
        casutaUser.setBounds(75, 45, 150, 25);
        frameAddUser.add(casutaUser);

        JPasswordField casutaPass;
        casutaPass = new JPasswordField();
        casutaPass.setBounds(75, 115, 150, 25);
        frameAddUser.add(casutaPass);


        JPasswordField casutaPass2;
        casutaPass2 = new JPasswordField();
        casutaPass2.setBounds(75, 165, 150, 25);
        frameAddUser.add(casutaPass2);

        JLabel etichUser;
        etichUser = new JLabel();
        etichUser.setText("Username");
        etichUser.setBounds(75, 20, 100, 25);
        etichUser.setVisible(true);
        frameAddUser.add(etichUser);


        JLabel etichPass;
        etichPass = new JLabel();
        etichPass.setText("Password");
        etichPass.setBounds(75, 90, 100, 25);
        etichPass.setVisible(true);
        frameAddUser.add(etichPass);

        JLabel etichPass2;
        etichPass2 = new JLabel();
        etichPass2.setText("Password again");
        etichPass2.setBounds(75, 140, 100, 25);
        etichPass2.setVisible(true);
        frameAddUser.add(etichPass2);


        JTextField casutaName;
        casutaName = new JTextField();
        casutaName.setBounds(275, 45, 200, 25);
        frameAddUser.add(casutaName);

        JLabel etichName;
        etichName = new JLabel();
        etichName.setText("Name");
        etichName.setBounds(275, 20, 100, 25);
        etichName.setVisible(true);
        frameAddUser.add(etichName);

        JTextField casutaPhone;
        casutaPhone = new JTextField();
        casutaPhone.setBounds(275, 95, 200, 25);
        frameAddUser.add(casutaPhone);

        JLabel etichPhone;
        etichPhone = new JLabel();
        etichPhone.setText("Phone");
        etichPhone.setBounds(275, 75, 100, 25);
        etichPhone.setVisible(true);
        frameAddUser.add(etichPhone);


        JTextField casutaEmail;
        casutaEmail = new JTextField();
        casutaEmail.setBounds(275, 145, 200, 25);
        frameAddUser.add(casutaEmail);

        JLabel etichEmail;
        etichEmail = new JLabel();
        etichEmail.setText("Email");
        etichEmail.setBounds(275, 125, 100, 25);
        etichEmail.setVisible(true);
        frameAddUser.add(etichEmail);


        JTextArea casutaAddress;
        casutaAddress = new JTextArea();
        casutaAddress.setBounds(275, 205, 200, 75);
        casutaAddress.setLineWrap(true);
        casutaAddress.setWrapStyleWord(true);
        casutaAddress.setAlignmentY(0);
        frameAddUser.add(casutaAddress);

        JLabel etichAddress;
        etichAddress = new JLabel();
        etichAddress.setText("Address");
        etichAddress.setBounds(275, 185, 100, 25);
        etichAddress.setVisible(true);
        frameAddUser.add(etichAddress);

        JLabel etichAdmin;
        etichAdmin = new JLabel();
        etichAdmin.setText("Admin");
        etichAdmin.setBounds(510, 45, 100, 25);
        etichAdmin.setVisible(true);
        frameAddUser.add(etichAdmin);

        JCheckBox checkAdmin;
        checkAdmin = new JCheckBox();
        checkAdmin.setBounds(550, 45, 100, 25);
        checkAdmin.setVisible(true);
        frameAddUser.add(checkAdmin);





        JButton buttonOk = new JButton("OK");
        buttonOk.setBounds(75, 220, 150, 25);
        frameAddUser.add(buttonOk);

        frameAddUser.setLayout(null);
        frameAddUser.setLocationRelativeTo(null);

        frameAddUser.setVisible(true);

Userdb readEdit = new Userdb();
        List<userDataComplete> u = readEdit.readUserDetails(id);
casutaName.setText(u.get(0).name);
        casutaUser.setText(u.get(0).username);
        casutaPhone.setText(Integer.toString(u.get(0).phone));
        casutaEmail.setText(u.get(0).email);
        casutaAddress.setText(u.get(0).address);
        checkAdmin.setSelected(u.get(0).admin);






        buttonOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                validate(casutaUser.getText(), casutaPass.getPassword(),casutaPass2.getPassword(),casutaName.getText(),casutaPhone.getText(),casutaEmail.getText(),casutaAddress.getText());


                Userdb checkUser = new Userdb();
                byte[] parolaDb = new byte[0];
                try {
                    parolaDb = checkUser.authp(casutaUser.getText());
                    if (parolaDb != null)
                    {
                        editOk = false;
                        JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password.");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();


                }






                if (editOk) {
                    System.out.println("Bag in db");


                    PassEncrypt secPass = new PassEncrypt();

                    try {
                        salt = secPass.generateSalt();
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        noSuchAlgorithmException.printStackTrace();
                    }


                    try {
                        SecPass = secPass.getEncryptedPassword(casutaPass.getPassword(), salt);
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        noSuchAlgorithmException.printStackTrace();
                    } catch (InvalidKeySpecException invalidKeySpecException) {
                        invalidKeySpecException.printStackTrace();
                    }


                    Userdb editUser = new Userdb();
                    int id1 = 0;
                    try {
                        id1 = editUser.lastID() + 1;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    //  System.out.println(casutaPass.getPassword().toString()+ "  "+ salt.toString());
                    try {
                        editUser.insert(id1, casutaUser.getText(), SecPass, salt, casutaName.getText(), Integer.parseInt(casutaPhone.getText().trim()), casutaEmail.getText(), casutaAddress.getText(), checkAdmin.isSelected());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                editOk = true; //to reset for another try

            }

        });


    }


    public  void validate(String user, char[] pass, char[]pass2,String name, String phone, String email, String address ) {

        if (user.length()==0 || pass.length < 1){
            editOk = false;


        }
        if (pass.length == pass2.length)
        {
            for (int i = 0; i< pass.length; i++){
                if (pass[i] != pass2[i]){
                    editOk = false;
                }
            }


        }
        if (name.length() <1 || phone.length() <1 || email.length()<1||address.length() <1) {
            editOk = false;

        }

        try
        {
            int ph = Integer.parseInt(phone.trim());

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid phone number");
            editOk = false;

        }



        if (editOk) {


        } else {
            JOptionPane.showMessageDialog(null, "Invalid/missing fields");
        }
    }









    }




