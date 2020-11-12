import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class users {
    public users() {
        JFrame frameMeniuU = new JFrame("User Management");
        frameMeniuU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMeniuU.setSize(400, 300);


        JButton buttonPassword = new JButton("Password Management");
        buttonPassword.setBounds(100, 30, 200, 25);
        frameMeniuU.add(buttonPassword);

        JButton buttonEdit = new JButton("Edit Users");
        buttonEdit.setBounds(100, 100, 200, 25);
        frameMeniuU.add(buttonEdit);


        JButton buttonAdd = new JButton("Add Users");
        buttonAdd.setBounds(100, 170, 200, 25);
        frameMeniuU.add(buttonAdd);


        frameMeniuU.setLayout(null);
        frameMeniuU.setLocationRelativeTo(null);

        frameMeniuU.setVisible(true);




        buttonPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               /* try {
                    new student();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }*/
            }

        });







        buttonEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               /* try {
                    new student();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }*/
            }

        });



        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    new AddUser();

            }

        });

    }
}
