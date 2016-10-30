/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.mainui;

import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.PlayerProfileUI;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.PlayerUI;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI.AdminUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LoginUI extends JFrame {

    private JLabel lblusername, lblpassword, lblcombobox;
    private TextField txtemail, txtpassword;
    private JButton btnlogin, btnsignup, btnexit;
    public static JComboBox<String> cmbloginoptions;
    String name;

    public LoginUI(String name) {

        this.name = name;
    }

    public LoginUI() {
        setTitle("Login Page");

        JLabel lblname = new JLabel("Login");
        lblname.setBounds(150, 10, 150, 30);
        add(lblname);

        lblname.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        lblname.setForeground(new java.awt.Color(153, 153, 0));

        lblusername = new JLabel("Enter Email");
        add(lblusername);
        lblusername.setBounds(20, 40, 100, 40);

        lblpassword = new JLabel("Enter Password");
        add(lblpassword);
        lblpassword.setBounds(20, 110, 100, 40);

        txtemail = new TextField();
        add(txtemail);
        txtemail.setBounds(180, 50, 180, 25);

        txtpassword = new TextField();
        add(txtpassword);
        txtpassword.setBounds(180, 120, 180, 25);

        lblcombobox = new JLabel("Choose User");
        add(lblcombobox);
        lblcombobox.setBounds(20, 180, 100, 40);

        cmbloginoptions = new JComboBox<>();
        cmbloginoptions.addItem("Player");
        cmbloginoptions.addItem("Admin");

        add(cmbloginoptions);
        cmbloginoptions.setBounds(180, 190, 180, 25);

        btnlogin = new JButton("Login");
        add(btnlogin);
        btnlogin.setBounds(200, 240, 130, 25);
        btnlogin.addActionListener(new loginEvent());

        btnsignup = new JButton("Sign Up");
        add(btnsignup);
        btnsignup.setBounds(30, 240, 130, 25);
        btnsignup.addActionListener(new signupEvent());

        btnexit = new JButton("Exit");
        add(btnexit);
        btnexit.setBounds(120, 310, 130, 25);
        btnexit.addActionListener(new exitEvent());

        setBounds(550, 150, 400, 530);
        setLayout(null);
        setBackground(Color.CYAN);
        setVisible(true);
        setBackground(new java.awt.Color(51, 255, 51));
        setForeground(new java.awt.Color(204, 255, 204));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public class loginEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                login();
                clearField();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public class signupEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //open register

            RegisterUI rg = new RegisterUI();
            dispose();
        }

    }

    public class exitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure?");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        }

    }

    public void login() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);

        if (txtemail.getText() == null && txtpassword.getText() == null) {

            JOptionPane.showMessageDialog(null, "Please enter Email and Password to login!");
        } else {
            if (cmbloginoptions.getSelectedItem() == "Admin") {
                //open admin form.
                String sql = "Select * from admin where admin_email='" + txtemail.getText() + "' and password='" + txtpassword.getText() + "'";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "login Successful");
                    dispose();

                    AdminUI ad = new AdminUI();

                    ad.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }
            } else if (cmbloginoptions.getSelectedItem() == "Player") {
                //open player form.
                String sql = "Select * from player where player_email='" + txtemail.getText() + "' and password='" + txtpassword.getText() + "'";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "login Successful");
                    dispose();
                    PlayerUI pl = new PlayerUI();

                    pl.SecondFrame("" + txtemail.getText());
                    pl.setVisible(true);
                    pl.setBounds(500, 100, 700, 600);

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }

            }
        }

    }

    public void clearField() {
        txtemail.setText("");
        txtpassword.setText("");

    }

}
