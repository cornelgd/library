import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class users {
    public users(int userId, boolean isAdmin) {
        JFrame frameMeniuU = new JFrame("User Management");
        frameMeniuU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMeniuU.setSize(400, 300);

        JButton buttonPassword = new JButton("Password Management");
        buttonPassword.setBounds(100, 30, 200, 25);
        frameMeniuU.add(buttonPassword);
if (isAdmin) {

    JButton buttonEdit = new JButton("Edit Users");
    buttonEdit.setBounds(100, 100, 200, 25);
    frameMeniuU.add(buttonEdit);

    buttonEdit.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new EditUser();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    });




        JButton buttonAdd = new JButton("Add Users");
        buttonAdd.setBounds(100, 170, 200, 25);
        frameMeniuU.add(buttonAdd);

    buttonAdd.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            new AddUser();

        }

    });








}

        frameMeniuU.setLayout(null);
        frameMeniuU.setLocationRelativeTo(null);

        frameMeniuU.setVisible(true);




        buttonPassword.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new changepass(userId);
            }

        });













    }
}
