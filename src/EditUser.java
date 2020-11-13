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

public class EditUser {
    public int selectedUserId;
    public String selectedUser;
    public EditUser() throws SQLException {


        JFrame frame = new JFrame("Edit User");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);


        DefaultTableModel model = new DefaultTableModel(){@Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
        };

      //  model.addColumn("ID");
        model.addColumn("User");

        JTable tableUser = new JTable(model);
        JScrollPane scrollUser = new JScrollPane(tableUser);
        tableUser.getColumnModel().getColumn(0).setPreferredWidth(50);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableUser.getModel());

        JTextField jtfFilter = new JTextField();
        jtfFilter.setBounds(10, 380, 150, 25);
        jtfFilter.setVisible(true);

        frame.add(new JLabel("Search"),
                BorderLayout.WEST);
        frame.add(jtfFilter);


        scrollUser.setBounds(10, 45, 150, 250);
        tableUser.setBounds(10, 45, 150, 250);
        tableUser.setOpaque(true);
        tableUser.setVisible(true);
        frame.add(tableUser);
        tableUser.setAutoCreateRowSorter(true);
        scrollUser.setViewportView(tableUser);
        scrollUser.setVisible(true);
        frame.add(scrollUser);


        tableUser.setRowSorter(rowSorter);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

Userdb readUsers = new Userdb();


        readUsers.getuserList();


        for ( int i=1;i<readUsers.userList.size();i++)
        {
                model.addRow(new Object[]{ readUsers.userList.get(i).username});

        }


        tableUser.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                tableUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 1) {
             selectedUser = tableUser.getValueAt(tableUser.rowAtPoint(evt.getPoint()),0).toString();
                    for ( int i=1;i<readUsers.userList.size();i++)
                    {
                        if (readUsers.userList.get(i).username == selectedUser){
                           selectedUserId =   readUsers.userList.get(i).id;}

                    }
                //     System.out.println("dblclick pe "+  selectedUser+ "userid="+ selectedUserId);
                    try {
                        new EditUserWindow(selectedUserId);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }});



                    jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

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



    }


}
