import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
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
                validate(casutaUser.getText(),casutaPass.getPassword());
            }

        });







    }




    public static void validate(String user, char[] pass) {
        boolean loginOk = true;
        String[] usernames = new String[2];
        String[] passwords = new String[usernames.length];

        usernames[0] = "a";
        passwords[0] = "pass";
        usernames[1] = "b";
        passwords[1] = "pass";


        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(user)) {
                if (passwords[i].length() != pass.length)
                    loginOk = false;
                for (int j = 0; j < passwords[i].length(); j++) {
                    try {
                        if (passwords[i].toCharArray()[j] != (pass[j])){
                            loginOk = false;
                            JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password0.");

                            return;
                        }
                    } catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password1.");
                        loginOk = false;
                        return;
                    }

                }
            }
            else loginOk = false;
        }

        if (loginOk) {
            System.out.println("Hooray, you did it!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect Username\nand/or Password2.");
        }
    }



}
