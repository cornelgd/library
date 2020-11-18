import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookLoan {

    public int selectedBookId, selectedUserIdS;
    public String selectedBook, selectedBookDate;
    public String dataAzi = String.valueOf(LocalDate.now());

    public BookLoan(int sId, String studName) throws SQLException {
//System.out.println("din bl"+sId);
        selectedUserIdS = sId;

        JFrame frameLoan = new JFrame("New Loan");
        frameLoan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameLoan.setSize(1000, 500);

        JLabel numeS;
        numeS = new JLabel();
        numeS.setText("Student ID "+selectedUserIdS+ " - "+studName);
        numeS.setBounds(375, 20, 250, 25);
        numeS.setVisible(true);
        frameLoan.add(numeS);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.addColumn("Id");
        model.addColumn("Title");
        model.addColumn("Author");


        DefaultTableModel modelL = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        modelL.addColumn("Id");
        modelL.addColumn("Title");
        modelL.addColumn("Author");
        modelL.addColumn("Loan date");


        JTable tableBooks = new JTable(model);
        JScrollPane scrollBooks = new JScrollPane(tableBooks);
        tableBooks.getColumnModel().getColumn(0).setPreferredWidth(50);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableBooks.getModel());

        JTable loanedBooks = new JTable(modelL);
        JScrollPane scrollLoanedBooks = new JScrollPane(loanedBooks);
        loanedBooks.getColumnModel().getColumn(0).setPreferredWidth(50);




        JTextField jtfFilter = new JTextField();
        jtfFilter.setBounds(10, 380, 150, 25);
        jtfFilter.setVisible(true);

        frameLoan.add(new JLabel("Search"),
                BorderLayout.WEST);
        frameLoan.add(jtfFilter);


        scrollBooks.setBounds(10, 45, 350, 250);
        tableBooks.setBounds(10, 45, 350, 250);
        tableBooks.setOpaque(true);
        tableBooks.setVisible(true);
        frameLoan.add(tableBooks);
        tableBooks.setAutoCreateRowSorter(true);
        scrollBooks.setViewportView(tableBooks);
        scrollBooks.setVisible(true);
        frameLoan.add(scrollBooks);


        JButton buttonF = new JButton("Finish");
        buttonF.setBounds(480, 400, 150, 25);
        frameLoan.add(buttonF);


        tableBooks.setRowSorter(rowSorter);

        frameLoan.setLocationRelativeTo(null);
        frameLoan.setLayout(null);
        frameLoan.setVisible(true);



        scrollLoanedBooks.setBounds(600, 45, 350, 250);
        loanedBooks.setBounds(600, 45, 350, 250);
        loanedBooks.setOpaque(true);
        loanedBooks.setVisible(true);
        frameLoan.add(loanedBooks);
       // loanedBooks.setAutoCreateRowSorter(true);
        scrollLoanedBooks.setViewportView(loanedBooks);
        scrollLoanedBooks.setVisible(true);
        frameLoan.add(scrollLoanedBooks);



        BookDb readBooks = new BookDb();


        readBooks.getBookList();


        for (int i = 1; i < readBooks.bookList.size(); i++) {
           // System.out.println("cartea "+i+" = "+ readBooks.bookList.get(i).date);
            if (readBooks.bookList.get(i).date == null || readBooks.bookList.get(i).date.equals(""))
            model.addRow(new Object[]{readBooks.bookList.get(i).id,readBooks.bookList.get(i).name, readBooks.bookList.get(i).author});

        }
        ArrayList<Integer> idList= new ArrayList<Integer>();

        Studentdb readStB = new Studentdb();


        List<StudLoanedBooks> studBooksList1 = readStB.readStudBooks(selectedUserIdS);

       if (studBooksList1.get(0).bookid1 > 0){
           for (int i = 1; i<readBooks.bookList.size(); i++ ){
              if (readBooks.bookList.get(i).id == studBooksList1.get(0).bookid1)
                  modelL.addRow(new Object[]{readBooks.bookList.get(i).id,readBooks.bookList.get(i).name, readBooks.bookList.get(i).author, studBooksList1.get(0).data1});
           }

        idList.add(studBooksList1.get(0).bookid1);}
        if (studBooksList1.get(0).bookid2 > 0){
            for (int i = 1; i<readBooks.bookList.size(); i++ ){
                if (readBooks.bookList.get(i).id == studBooksList1.get(0).bookid2)
                    modelL.addRow(new Object[]{readBooks.bookList.get(i).id,readBooks.bookList.get(i).name, readBooks.bookList.get(i).author,studBooksList1.get(0).data2});
            }        idList.add(studBooksList1.get(0).bookid2);}
        if (studBooksList1.get(0).bookid3 > 0){
            for (int i = 1; i<readBooks.bookList.size(); i++ ){
                if (readBooks.bookList.get(i).id == studBooksList1.get(0).bookid3)
                    modelL.addRow(new Object[]{readBooks.bookList.get(i).id,readBooks.bookList.get(i).name, readBooks.bookList.get(i).author, studBooksList1.get(0).data3});
            }        idList.add(studBooksList1.get(0).bookid3);}
        if (studBooksList1.get(0).bookid4 > 0){
            for (int i = 1; i<readBooks.bookList.size(); i++ ){
                if (readBooks.bookList.get(i).id == studBooksList1.get(0).bookid4)
                    modelL.addRow(new Object[]{readBooks.bookList.get(i).id,readBooks.bookList.get(i).name, readBooks.bookList.get(i).author, studBooksList1.get(0).data4});
            }        idList.add(studBooksList1.get(0).bookid4);}
        if (studBooksList1.get(0).bookid5 > 0){
            for (int i = 1; i<readBooks.bookList.size(); i++ ){
                if (readBooks.bookList.get(i).id == studBooksList1.get(0).bookid5)
                    modelL.addRow(new Object[]{readBooks.bookList.get(i).id,readBooks.bookList.get(i).name, readBooks.bookList.get(i).author, studBooksList1.get(0).data5});
            }        idList.add(studBooksList1.get(0).bookid5);}



      //  System.out.println("dddd1 "+selectedBookDate);



        tableBooks.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                tableBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 1) {
                    int selRow = tableBooks.rowAtPoint(evt.getPoint());
                    selectedBook = tableBooks.getValueAt(selRow, 1).toString();
                    for (int i = 1; i < readBooks.bookList.size(); i++) {
                        if (readBooks.bookList.get(i).name == selectedBook) {

                            selectedBookId = readBooks.bookList.get(i).id;
                            selectedBookDate = readBooks.bookList.get(i).date;


                        if (idList.size() <5) idList.add(selectedBookId);
                        }

                    }
                   if (modelL.getRowCount() < 5) {

                       if (selectedBookDate == null || selectedBookDate.equals("")) {
                           modelL.addRow(new Object[]{selectedBookId,selectedBook,  tableBooks.getValueAt(tableBooks.rowAtPoint(evt.getPoint()), 2).toString(), dataAzi});
                       }
                       else {
                           modelL.addRow(new Object[]{selectedBookId,selectedBook, tableBooks.getValueAt(tableBooks.rowAtPoint(evt.getPoint()), 2).toString(), selectedBookDate});
                       }
                       model.removeRow(selRow);
                      // for(int fruit:idList) System.out.println(fruit);


                   }
                    else {

                       JOptionPane.showMessageDialog(null, "Maximum number of loaned books is 5");


                   }

                }
            }
        });



        loanedBooks.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt1) {

                loanedBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt1.getClickCount() > 1) {
                    int selRowS = loanedBooks.rowAtPoint(evt1.getPoint());



                    model.addRow(new Object[]{loanedBooks.getValueAt(loanedBooks.rowAtPoint(evt1.getPoint()), 0).toString(), loanedBooks.getValueAt(loanedBooks.rowAtPoint(evt1.getPoint()), 1).toString(),loanedBooks.getValueAt(loanedBooks.rowAtPoint(evt1.getPoint()), 2).toString()});

                    modelL.removeRow(selRowS);
idList.remove(selRowS);



                }
            }
        });











        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {

                    rowSorter.setRowFilter(null);
                } else {

                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });






        buttonF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

BookDb loanB = new BookDb();
                Studentdb loanS = new Studentdb();

                String date1, date2,date3,date4,date5;
int  bid1, bid2,bid3,bid4,bid5;


                for (int i=0; i<idList.size(); i++) {
                    System.out.println(i+" "+modelL.getValueAt(i,3));


                     loanB.updateB(studName, (String) modelL.getValueAt(i,3), idList.get(i));


                }
                //daca scot din dreapta nu mai am ce id sa sterg...
              //  for (int i = idList.size(); i<5; i++)   loanB.updateB(null, null, 0);


                try { date1 = (String) modelL.getValueAt(0,3);
                bid1 = idList.get(0); }
                        catch (Exception e1){
                            bid1 = 0;
                             date1 = null;
                        }
                try { date2 = (String) modelL.getValueAt(1,3);
                    bid2 = idList.get(1);}
                catch (Exception e1){
                    bid2 = 0;
                     date2 = null;
                }
                try { date3 = (String) modelL.getValueAt(2,3);
                    bid3 = idList.get(2);}
                catch (Exception e1){
                     date3 = null;
                    bid3 = 0;
                }
                try { date4 = (String) modelL.getValueAt(3,3);
                    bid4 = idList.get(3);}
                catch (Exception e1){
                    bid4 = 0;
                     date4 = null;
                }
                try { date5 = (String) modelL.getValueAt(4,3);
                    bid5 = idList.get(4);}
                catch (Exception e1){
                    bid5 = 0;
                     date5 = null;
                }



                   loanS.updateS(bid1,date1 ,bid2,date2 ,bid3,date3 ,bid4,date4 ,bid5,date5 ,selectedUserIdS);





            }
        });







    }

}