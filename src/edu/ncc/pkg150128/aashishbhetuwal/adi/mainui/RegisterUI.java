/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.mainui;

import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.PlayerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.PlayerDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Player;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class RegisterUI extends JFrame {

    private JLabel lblplayer_id, lblplayer_name, lblplayer_email, lblpassword, lblconpassword, lblplayer_location, lblpreffered_time, lblpreffered_category, lblnoofplayers;
    private TextField txtplayer_id, txtplayer_name, txtplayer_email, txtpassword, txtconpassword, txtplayer_location, txtpreffered_time, txtnoofplayers;
    public static JComboBox<String> cmbgamecategory;
    private JButton btnsignup, btnlogin;

    public RegisterUI() {
        setTitle("Sign Up");

        JLabel lblname = new JLabel("Sign up");
        lblname.setBounds(150, 10, 150, 30);
        add(lblname);

        lblname.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblname.setForeground(new java.awt.Color(153, 153, 0));

        lblplayer_name = new JLabel("Enter Name");
        add(lblplayer_name);
        lblplayer_name.setBounds(20, 90, 100, 40);

        lblplayer_email = new JLabel("Enter an Email");
        add(lblplayer_email);
        lblplayer_email.setBounds(20, 150, 100, 40);

        lblpassword = new JLabel("Enter a Password");
        add(lblpassword);
        lblpassword.setBounds(20, 210, 150, 40);

        lblconpassword = new JLabel("Confirm password");
        add(lblconpassword);
        lblconpassword.setBounds(20, 270, 130, 40);

        lblplayer_location = new JLabel("Enter location");
        add(lblplayer_location);
        lblplayer_location.setBounds(20, 330, 100, 40);

        txtplayer_name = new TextField();
        add(txtplayer_name);
        txtplayer_name.setBounds(180, 100, 230, 25);

        txtplayer_email = new TextField();
        add(txtplayer_email);
        txtplayer_email.setBounds(180, 160, 230, 25);

        txtpassword = new TextField();
        add(txtpassword);
        txtpassword.setBounds(180, 220, 230, 25);

        txtconpassword = new TextField();
        add(txtconpassword);
        txtconpassword.setBounds(180, 280, 230, 25);

        txtplayer_location = new TextField();
        add(txtplayer_location);
        txtplayer_location.setBounds(180, 340, 230, 25);

     
        //buttons
        btnlogin = new JButton("Login");
        add(btnlogin);
        btnlogin.setBounds(30, 540, 150, 25);
        btnlogin.addActionListener(new loginEvent());

        btnsignup = new JButton("Sign Up");
        add(btnsignup);
        btnsignup.setBounds(230, 540, 150, 25);
        btnsignup.addActionListener(new signupEvent());

        setBounds(400, 30, 700, 700);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public class loginEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginUI lg = new LoginUI();
            dispose();
        }

    }

    public class signupEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Enter register code here

            PlayerDAO pd = new PlayerDAOImpl();

            Player p = new Player();
            p.setPlayername(txtplayer_name.getText());

            p.setPlayeremail(txtplayer_email.getText());
            p.setPlayerlocation(txtplayer_location.getText());
            p.setPassword(txtpassword.getText());
            p.setConpassword(txtconpassword.getText());

            if (txtplayer_email == null && txtpassword == null && txtplayer_name == null && txtplayer_location == null && txtconpassword.getText() == null) {
                JOptionPane.showMessageDialog(null, "Please enter Email and Password to register");

            } else {

                if (txtpassword.getText().equals(txtconpassword.getText())) {

                    try {
                        pd.insert(p);
                        JOptionPane.showMessageDialog(null, "Sign up Successful. Please Login to continue");
                        clearFields();
                    } catch (ClassNotFoundException | SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Password did not match!");

                }

            }
        }

    }

    public void clearFields() {

        txtplayer_name.setText("");
        txtplayer_email.setText("");
        txtpassword.setText("");
        txtconpassword.setText("");
        txtplayer_location.setText("");
    }

}
