import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AddStudent {
           boolean addOk = true;

        public AddStudent() {

            JFrame frameAddUser = new JFrame("Add student");
            frameAddUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameAddUser.setSize(650, 350);



            JTextField casutaName;
            casutaName = new JTextField();
            casutaName.setBounds(100, 45, 200, 25);
            frameAddUser.add(casutaName);

            JLabel etichName;
            etichName = new JLabel();
            etichName.setText("Name");
            etichName.setBounds(100, 20, 100, 25);
            etichName.setVisible(true);
            frameAddUser.add(etichName);

            JTextField casutaPhone;
            casutaPhone = new JTextField();
            casutaPhone.setBounds(100, 95, 200, 25);
            frameAddUser.add(casutaPhone);

            JLabel etichPhone;
            etichPhone = new JLabel();
            etichPhone.setText("Phone");
            etichPhone.setBounds(100, 75, 100, 25);
            etichPhone.setVisible(true);
            frameAddUser.add(etichPhone);


            JTextField casutaEmail;
            casutaEmail = new JTextField();
            casutaEmail.setBounds(100, 145, 200, 25);
            frameAddUser.add(casutaEmail);

            JLabel etichEmail;
            etichEmail = new JLabel();
            etichEmail.setText("Email");
            etichEmail.setBounds(100, 125, 100, 25);
            etichEmail.setVisible(true);
            frameAddUser.add(etichEmail);


            JTextArea casutaAddress;
            casutaAddress = new JTextArea();
            casutaAddress.setBounds(100, 205, 200, 75);
            casutaAddress.setLineWrap(true);
            casutaAddress.setWrapStyleWord(true);
            casutaAddress.setAlignmentY(0);
            frameAddUser.add(casutaAddress);

            JLabel etichAddress;
            etichAddress = new JLabel();
            etichAddress.setText("Address");
            etichAddress.setBounds(100, 185, 100, 25);
            etichAddress.setVisible(true);
            frameAddUser.add(etichAddress);














            JButton buttonOk = new JButton("OK");
            buttonOk.setBounds(350, 255, 150, 25);
            frameAddUser.add(buttonOk);

            frameAddUser.setLayout(null);
            frameAddUser.setLocationRelativeTo(null);

            frameAddUser.setVisible(true);

            buttonOk.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    validate(casutaName.getText(),casutaPhone.getText(),casutaEmail.getText(),casutaAddress.getText());


                    Studentdb checkStudent = new Studentdb();
                    boolean parolaDb ;
                    try {
                        parolaDb = checkStudent.authp(casutaName.getText());
                        if (!parolaDb)
                        {
                            addOk = false;
                            JOptionPane.showMessageDialog(null, "Existing name");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }






                    if (addOk) {
                        System.out.println("Bag in db");





                        Studentdb addStudent = new Studentdb();
                        int id1 = 0;
                        try {
                            id1 = addStudent.lastID() + 1;
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                        try {
                            addStudent.insert(id1, casutaName.getText(), Integer.parseInt(casutaPhone.getText().trim()), casutaEmail.getText(), casutaAddress.getText());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                    addOk = true; //to reset for another try

                }

            });


        }


        public  void validate(String name, String phone, String email, String address ) {

            if (name.length()==0 ){
                addOk = false;


            }

            if (name.length() <1 || phone.length() <1 || email.length()<1||address.length() <1) {
                addOk = false;

            }

            try
            {
                int ph = Integer.parseInt(phone.trim());

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Invalid phone number");
                addOk = false;

            }



            if (addOk) {


            } else {
                JOptionPane.showMessageDialog(null, "Invalid/missing fields");
            }
        }


    }















