/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houseresistrationsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Chew Teng
 */

public class MainInterface {
    
    static ArrayList<House> houseList = new ArrayList<House>();
    static JTextField jtfname, jtfloc, jtfprice;
    static DefaultTableModel model; 
    static String ownerName, houseLoc;
    static double housePrice;
    
    public MainInterface(){
        
        // create JFrame and JTable
        JFrame frame = new JFrame("Main Application Interface");
        JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model 
        String[] colNames = {"Owner Name", "House Location", "House Price"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colNames);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.cyan);
        table.getTableHeader().setFont(new Font("", Font.BOLD, 18));

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 240, 700, 500);
        frame.add(pane);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.white);
        table.setForeground(Color.BLACK);
        Font font = new Font("", Font.PLAIN, 18);
        table.setFont(font);
        table.setRowHeight(28);
        table.setShowGrid(true);
        table.setGridColor(Color.DARK_GRAY);

        // create JLabel
        JLabel lblname = new JLabel("Owner Name");
        JLabel lblloc = new JLabel("House Location");
        JLabel lblprice = new JLabel("House Price");
        lblname.setBounds(20, 40, 180, 20);
        lblloc.setBounds(20, 80, 180, 20);
        lblprice.setBounds(20, 120, 180, 20);
        // add JLabel to the jframe
        frame.add(lblname);
        frame.add(lblloc);
        frame.add(lblprice);
        // set font size
        lblname.setFont(new Font(" ", Font.BOLD, 18));
        lblloc.setFont(new Font(" ", Font.BOLD, 18));
        lblprice.setFont(new Font(" ", Font.BOLD, 18));

        // create JButtons
        JButton btninsert = new JButton("Insert");
        JButton btndelete = new JButton("Delete");
        JButton btnupdate = new JButton("Update");
        JButton btnsearch = new JButton("Search");
        JButton btnclear = new JButton("Clear");
        btninsert.setBounds(20, 180, 120, 20);
        btndelete.setBounds(160, 180, 120, 20);
        btnupdate.setBounds(300, 180, 120, 20);
        btnsearch.setBounds(440, 180, 120, 20);
        btnclear.setBounds(580, 180, 120, 20);
        // add JButtons to the jframe
        frame.add(btninsert);
        frame.add(btndelete);
        frame.add(btnupdate);
        frame.add(btnsearch);
        frame.add(btnclear);
        // set font size
        btninsert.setFont(new Font(" ", Font.BOLD, 18));
        btndelete.setFont(new Font(" ", Font.BOLD, 18));
        btnupdate.setFont(new Font(" ", Font.BOLD, 18));
        btnsearch.setFont(new Font(" ", Font.BOLD, 18));
        btnclear.setFont(new Font(" ", Font.BOLD, 18));
        
        // create JTextFields
        jtfname = new JTextField();
        jtfloc = new JTextField();
        jtfprice = new JTextField();
        jtfname.setBounds(200, 40, 200, 20);
        jtfloc.setBounds(200, 80, 200, 20);
        jtfprice.setBounds(200, 120, 200, 20);
        // add JTextFields to the jframe
        frame.add(jtfname);
        frame.add(jtfloc);
        frame.add(jtfprice);

        frame.setResizable(false);
        frame.setSize(750, 800);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // button add row
        btninsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerName = jtfname.getText();
                houseLoc = jtfloc.getText();
                housePrice = Double.parseDouble(jtfprice.getText());

                houseList.add(new House(ownerName, houseLoc, housePrice));

                clearField();
                writeData();

            }
        });

        // button remove row
        btndelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogButton = JOptionPane.YES_NO_OPTION;

                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Delete this data", "Delete", dialogButton);

                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                if (i >= 0) {
                    // remove a row from jtable
                    houseList.remove(i);
                } else {
                    System.out.println("Delete Error");
                }

                writeData();
                clearField();
            }
        });

        // get selected row data from table to textfields 
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // j = the index of the selected row
                int j = table.getSelectedRow();

                jtfname.setText(model.getValueAt(j, 0).toString());
                jtfloc.setText(model.getValueAt(j, 1).toString());
                jtfprice.setText(model.getValueAt(j, 2).toString());

            }
        });

        // button update row
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ownerName = jtfname.getText();
                houseLoc = jtfloc.getText();
                housePrice = Double.parseDouble(jtfprice.getText());

                // i = the index of the selected row
                int i = table.getSelectedRow();
                int index = -1;
                boolean found = false;

                if (i >= 0) {
                    {
                        houseList.get(i).setNewName(ownerName);
                        houseList.get(i).setNewLoc(houseLoc);
                        houseList.get(i).setNewPrice(housePrice);

                    }
                } else {
                    System.out.println("Update Error");
                }

                writeData();
                clearField();

            }
        });

        // button search row
        btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean searching = false;
                boolean result = houseList.isEmpty();

                String ownerName = JOptionPane.showInputDialog("Search Owner Name:");

                for (int i = 0; i < houseList.size(); i++) {
                    if (houseList.get(i).getOwnerName().equalsIgnoreCase(ownerName)) {
                        searching = true;
                        JOptionPane.showMessageDialog(btnsearch, "Found!!!", "Search House", 0);

                        jtfname.setText(houseList.get(i).getOwnerName());
                        jtfloc.setText(houseList.get(i).getHouseLoc());
                        jtfprice.setText(String.valueOf(houseList.get(i).getHousePrice()));
                        break;
                    }
                }
                if (searching == false) {
                    JOptionPane.showMessageDialog(btnsearch, "Not Found!!!", "Search House", 0);
                }

            }

        });

        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearField();

            }
        });

        loadData();
    }

    private void clearField() {

        jtfname.setText("");
        jtfloc.setText("");
        jtfprice.setText("");

    }

    private static void writeData() {
        try {

            File outFile = new File("housedata.txt");
            FileWriter outFileStream = new FileWriter(outFile);
            PrintWriter outStream = new PrintWriter(outFileStream);

            for (int i = 0; i < houseList.size(); i++) {

                outStream.print(houseList.get(i).getOwnerName() + ",");
                outStream.print(houseList.get(i).getHouseLoc() + ",");
                outStream.println(houseList.get(i).getHousePrice());

            }

            outStream.close();

        } catch (Exception ex) {

        }

        model.setRowCount(0);//reset data model

        for (int i = 0; i < houseList.size(); i++) {

            Object[] objs = {houseList.get(i).getOwnerName(),
                houseList.get(i).getHouseLoc(),
                houseList.get(i).getHousePrice()};

            model.addRow(objs);
        }
    }

    private void loadData() {

        try {

            File inFile = new File("housedata.txt");

            Scanner scanner = new Scanner(inFile);
            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                String housearray[] = data.toString().split(",");
                houseList.add(new House(housearray[0], housearray[1], 
                        Double.parseDouble(housearray[2])));
            }

        } catch (Exception ex) {

        }
        model.setRowCount(0);//reset data model

        for (int i = 0; i < houseList.size(); i++) {

            Object[] objs = {houseList.get(i).getOwnerName(),
                houseList.get(i).getHouseLoc(),
                houseList.get(i).getHousePrice()};

            model.addRow(objs);
        }

    }
}
