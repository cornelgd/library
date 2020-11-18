import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Books {


    public Books() {
        JFrame frameMeniuU = new JFrame("Books Management");
        frameMeniuU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMeniuU.setSize(400, 300);



        JButton buttonBookLoans = new JButton("Loan Book");
        buttonBookLoans.setBounds(100, 30, 200, 25);
        frameMeniuU.add(buttonBookLoans);

        buttonBookLoans.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new SelUser();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });




        JButton buttonAddNewBooks = new JButton("Add Books");
        buttonAddNewBooks.setBounds(100, 100, 200, 25);
        frameMeniuU.add(buttonAddNewBooks);

        buttonAddNewBooks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

               new AddBook();

            }

        });










        frameMeniuU.setLayout(null);
        frameMeniuU.setLocationRelativeTo(null);

        frameMeniuU.setVisible(true);


















    }







}
