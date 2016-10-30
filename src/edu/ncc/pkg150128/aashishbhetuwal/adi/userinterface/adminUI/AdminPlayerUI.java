/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI;

import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Player;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Toshiba
 */
public class AdminPlayerUI extends JFrame {

    JLabel lblplayerid, lblplayername, lblintro, lblplayerlocation, lblplayeremail;
    TextField txtplayerid, txtplayername, txtplayeremail, txtplayerlocation;
    JButton btnsearch;

    public AdminPlayerUI() {

        lblintro = new JLabel("Players");
        add(lblintro);
        lblintro.setBounds(200, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        lblplayerid = new JLabel("Player ID");
        add(lblplayerid);
        lblplayerid.setBounds(30, 150, 100, 40);

        lblplayername = new JLabel("Player Name");
        add(lblplayername);
        lblplayername.setBounds(30, 220, 150, 40);

        lblplayeremail = new JLabel("Player Email");
        add(lblplayeremail);
        lblplayeremail.setBounds(30, 260, 150, 40);

        lblplayerlocation = new JLabel("Player Location");
        add(lblplayerlocation);
        lblplayerlocation.setBounds(30, 300, 150, 40);

        txtplayerid = new TextField();
        add(txtplayerid);
        txtplayerid.setBounds(180, 160, 150, 25);

        txtplayername = new TextField();
        add(txtplayername);
        txtplayername.setBounds(180, 230, 230, 25);

        txtplayeremail = new TextField();
        add(txtplayeremail);
        txtplayeremail.setBounds(180, 270, 230, 25);

        txtplayerlocation = new TextField();
        add(txtplayerlocation);
        txtplayerlocation.setBounds(180, 310, 230, 25);

        btnsearch = new JButton("Search");
        add(btnsearch);
        btnsearch.setBounds(350, 160, 130, 25);
        btnsearch.addActionListener(new searchEvent());

        JButton btnback = new JButton("Back");
        add(btnback);
        btnback.setBounds(170, 420, 130, 25);
        btnback.addActionListener(new backEvent());

        setBounds(400, 30, 600, 550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public class searchEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Player pl = new Player();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
                //JOptionPane.showMessageDialog(null, "Connection Successful");
                String sql = "Select * from player where player_id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(txtplayerid.getText()));

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {

                    pl.setPlayername(rs.getString("player_name"));
                    pl.setPlayeremail(rs.getString("player_email"));
                    pl.setPlayerlocation(rs.getString("player_location"));

                }

                conn.close();

                txtplayername.setText(pl.getPlayername());
                txtplayeremail.setText(pl.getPlayeremail());
                txtplayerlocation.setText(pl.getPlayerlocation());

            } catch (ClassNotFoundException | SQLException se) {
                System.out.println(se.getMessage());
            }

        }
    }

    public class backEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            AdminUI ad = new AdminUI();

            ad.setVisible(true);

            dispose();

        }

    }

}
