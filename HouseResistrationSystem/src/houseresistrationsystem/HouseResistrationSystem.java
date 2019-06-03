/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package houseresistrationsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Chew Teng
 */
public class HouseResistrationSystem {
    
    public HouseResistrationSystem() {
       
        JFrame frame = new JFrame("LoginScreen");

        JLabel lblname = new JLabel("UserName");
        JLabel lblpass = new JLabel("Password");
        lblname.setBounds(20, 20, 100, 20);
        lblpass.setBounds(20, 60, 100, 20);
        frame.add(lblname);
        frame.add(lblpass);

        JTextField jtfname = new JTextField();
        JTextField jtfpass = new JTextField();
        jtfname.setBounds(100, 20, 200, 20);
        jtfpass.setBounds(100, 60, 200, 20);
        frame.add(jtfname);
        frame.add(jtfpass);

        JButton btnlogin = new JButton("Login");
        JButton btncancel = new JButton("Cancel");
        btnlogin.setBounds(100, 100, 100, 20);
        btncancel.setBounds(200, 100, 100, 20);
        frame.add(btnlogin);
        frame.add(btncancel);

        frame.setResizable(false);
        frame.setSize(350, 200);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                
                try{
                    String a, b;
                    String uname = jtfname.getText();
                    String psd = jtfpass.getText();
                    
                    File inFile = new File("loginpsd.txt");
                    FileReader filereader = new FileReader(inFile);
                    BufferedReader bufReader = new BufferedReader(filereader);
                    a = bufReader.readLine();
                    b = bufReader.readLine();
                    jtfname.setText(a);
                    jtfpass.setText(b);
                    bufReader.close();
                    
                    if (uname.equals("hngcteng") && (psd.equals("1221"))) {
                        JOptionPane.showMessageDialog(null, "Welcome to the System");
                        frame.setVisible(false);
                        new MainInterface();
                    } 
                    
                }catch(Exception ex){
                    System.out.println("Error");
                }
            }
        });

        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        new HouseResistrationSystem();
    }
    
}
