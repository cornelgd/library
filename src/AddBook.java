import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddBook {



    boolean addOk = true;

    public AddBook() {

        JFrame frameAddUser = new JFrame("Add new book");
        frameAddUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAddUser.setSize(650, 350);



        JTextField casutaName;
        casutaName = new JTextField();
        casutaName.setBounds(100, 45, 200, 25);
        frameAddUser.add(casutaName);

        JLabel etichName;
        etichName = new JLabel();
        etichName.setText("Title");
        etichName.setBounds(100, 20, 100, 25);
        etichName.setVisible(true);
        frameAddUser.add(etichName);

        JTextField casutaAuthor;
        casutaAuthor = new JTextField();
        casutaAuthor.setBounds(100, 95, 200, 25);
        frameAddUser.add(casutaAuthor);

        JLabel etichAuthor;
        etichAuthor = new JLabel();
        etichAuthor.setText("Author");
        etichAuthor.setBounds(100, 75, 100, 25);
        etichAuthor.setVisible(true);
        frameAddUser.add(etichAuthor);


        JTextField casutaYear;
        casutaYear = new JTextField();
        casutaYear.setBounds(100, 145, 200, 25);
        frameAddUser.add(casutaYear);

        JLabel etichYear;
        etichYear = new JLabel();
        etichYear.setText("Year");
        etichYear.setBounds(100, 125, 100, 25);
        etichYear.setVisible(true);
        frameAddUser.add(etichYear);

        JTextField casutaPcs;
        casutaPcs = new JTextField();
        casutaPcs.setBounds(100, 195, 200, 25);
        frameAddUser.add(casutaPcs);

        JLabel etichPcs;
        etichPcs = new JLabel();
        etichPcs.setText("How many to add to inventory?");
        etichPcs.setBounds(100, 175, 100, 25);
        etichPcs.setVisible(true);
        frameAddUser.add(etichPcs);














        JButton buttonOk = new JButton("OK");
        buttonOk.setBounds(350, 255, 150, 25);
        frameAddUser.add(buttonOk);

        frameAddUser.setLayout(null);
        frameAddUser.setLocationRelativeTo(null);

        frameAddUser.setVisible(true);

        buttonOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                validate(casutaName.getText(),casutaAuthor.getText(),casutaYear.getText(),casutaPcs.getText());


                BookDb checkBook = new BookDb();
                boolean parolaDb ;
                try {
                    parolaDb = checkBook.authp(casutaName.getText());
                    if (!parolaDb)
                    {
                        addOk = false;
                        JOptionPane.showMessageDialog(null, "Existing book");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }






                if (addOk) {
                    System.out.println("Bag in db");





                    BookDb addBook = new BookDb();
                    int id1 = 0;
                    try {
                        id1 = addBook.lastID() + 1;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                  int  pcs = Integer.parseInt(casutaPcs.getText().trim());


    do {


        try {
            addBook.insert(id1, casutaName.getText(), casutaAuthor.getText(), Integer.parseInt(casutaYear.getText().trim()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
pcs--;
id1++;
    } while (pcs >0);

                }
                addOk = true; //to reset for another try

            }

        });


    }


    public  void validate(String name, String author, String year, String pcs ) {



        if (name.length() <1 || author.length() <1 || year.length()<1) {
            addOk = false;

        }
        try
        {
            int ph1 = Integer.parseInt(pcs.trim());
            if (ph1 <0 ) addOk = false;
        }
        catch (Exception e)
        {
            // JOptionPane.showMessageDialog(null, "Invalid publish year");
            addOk = false;

        }
        try
        {
            int ph = Integer.parseInt(year.trim());

        }
        catch (Exception e)
        {
           // JOptionPane.showMessageDialog(null, "Invalid publish year");
            addOk = false;

        }



        if (addOk) {


        } else {
            JOptionPane.showMessageDialog(null, "Invalid/missing fields");
        }
    }


}











