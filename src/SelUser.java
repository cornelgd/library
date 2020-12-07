import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SelUser {


    public int selectedUserIdS;
    public String selectedUserS;

    public  SelUser() throws SQLException {


        JFrame frame = new JFrame("Find student");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);


        DefaultTableModel modelS = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        //  model.addColumn("ID");
        modelS.addColumn("Student name");

        JTable tableStud = new JTable(modelS);
        JScrollPane scrollStud = new JScrollPane(tableStud);
        tableStud.getColumnModel().getColumn(0).setPreferredWidth(50);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableStud.getModel());

        JTextField jtfFilterS = new JTextField();
        jtfFilterS.setBounds(10, 380, 150, 25);
        jtfFilterS.setVisible(true);

        frame.add(new JLabel("Search"),
                BorderLayout.WEST);
        frame.add(jtfFilterS);


        scrollStud.setBounds(40, 45, 450, 250);
        tableStud.setBounds(40, 45, 450, 250);
        tableStud.setOpaque(true);
        tableStud.setVisible(true);
        frame.add(tableStud);
        tableStud.setAutoCreateRowSorter(true);
        scrollStud.setViewportView(tableStud);
        scrollStud.setVisible(true);
        frame.add(scrollStud);


        tableStud.setRowSorter(rowSorter);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        Studentdb readUsers = new Studentdb();


        readUsers.getuserList();


        for (int i = 1; i < readUsers.userList.size(); i++) {
            modelS.addRow(new Object[]{readUsers.userList.get(i).username});

        }
       // System.out.println("din seluser "+  selectedUserIdS);

        tableStud.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                tableStud.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 1) {
                    selectedUserS = tableStud.getValueAt(tableStud.rowAtPoint(evt.getPoint()), 0).toString();
                    for (int i = 1; i < readUsers.userList.size(); i++) {
                        if (readUsers.userList.get(i).username == selectedUserS) {
                            selectedUserIdS = readUsers.userList.get(i).id;
                        }

                    }

                         System.out.println("dblclick pe "+  selectedUserIdS);
frame.dispose();
                    try {
                        new BookLoan(selectedUserIdS,selectedUserS);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                        /*try {
                            //new EditUserWindow(selectedUserIdS);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }*/

                }
            }
        });


        jtfFilterS.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilterS.getText();
                if (text.trim().length() == 0) {

                    rowSorter.setRowFilter(null);
                } else {

                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilterS.getText();

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
      //  System.out.println("din seluser "+  selectedUserIdS);
    }
}



