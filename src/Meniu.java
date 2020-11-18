import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Meniu {
    public  Meniu(int userId, boolean isAdmin) {

        JFrame frameMeniu = new JFrame("Welcome");
        frameMeniu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMeniu.setSize(400,300);


        JButton buttonStudent=new JButton("Student list management");
        buttonStudent.setBounds(100,30,200,25);
        frameMeniu.add(buttonStudent);

        JButton buttonBooks=new JButton("Book inventory management");
        buttonBooks.setBounds(100,105,200,25);
        frameMeniu.add(buttonBooks);

        JButton buttonUsers=new JButton("User management");
        buttonUsers.setBounds(100,175,200,25);
        frameMeniu.add(buttonUsers);




        frameMeniu.setLayout(null);
        frameMeniu.setLocationRelativeTo(null);

        frameMeniu.setVisible(true);


        buttonStudent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    new Student();

            }

        });


        buttonBooks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                    new Books();



            }

        });


        buttonUsers.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new users(userId, isAdmin);


            }

        });
    }
}
