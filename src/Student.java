import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Student {



        public Student() {
            JFrame frameMeniuU = new JFrame("Student Management");
            frameMeniuU.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameMeniuU.setSize(400, 300);



                JButton buttonEditLoans = new JButton("Student Book Loans");
                buttonEditLoans.setBounds(100, 30, 200, 25);
                frameMeniuU.add(buttonEditLoans);

                buttonEditLoans.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                           // new StudentLoan();

                    }

                });




                JButton buttonAddStudent = new JButton("Add Students");
                buttonAddStudent.setBounds(100, 100, 200, 25);
                frameMeniuU.add(buttonAddStudent);

                buttonAddStudent.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new AddStudent();

                    }

                });










            frameMeniuU.setLayout(null);
            frameMeniuU.setLocationRelativeTo(null);

            frameMeniuU.setVisible(true);


















        }
    }















