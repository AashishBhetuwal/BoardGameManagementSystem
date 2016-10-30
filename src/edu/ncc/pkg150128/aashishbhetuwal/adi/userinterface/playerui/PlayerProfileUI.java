/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui;

import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.PlayerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.PlayerDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Player;
import edu.ncc.pkg150128.aashishbhetuwal.adi.mainui.LoginUI;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class PlayerProfileUI extends JFrame {

    JLabel lblid, lblplayer_name, lblplayer_email, lblpassword, lblplayer_location;
    TextField txtplayer_id, txtplayer_name, txtplayer_email, txtpassword, txtplayer_location;
    JButton btnupdate, btndelete, btnback;
    String name;

    public PlayerProfileUI(String name) {
        this.name = name;

        setTitle("Player Profile");

        JLabel lblintro = new JLabel("Player Profile");
        add(lblintro);
        lblintro.setBounds(200, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        lblid = new JLabel("ID");
        add(lblid);
        lblid.setBounds(20, 90, 100, 40);

        lblplayer_name = new JLabel("Player Name");
        add(lblplayer_name);
        lblplayer_name.setBounds(20, 150, 100, 40);

        lblplayer_email = new JLabel("Player Email");
        add(lblplayer_email);
        lblplayer_email.setBounds(20, 210, 100, 40);

        lblpassword = new JLabel("Password");
        add(lblpassword);
        lblpassword.setBounds(20, 270, 150, 40);

        lblplayer_location = new JLabel("Player location");
        add(lblplayer_location);
        lblplayer_location.setBounds(20, 330, 100, 40);

        //textfield
        Player pe = new Player();
        txtplayer_id = new TextField(pe.getPlayername());
        add(txtplayer_id);
        txtplayer_id.setBounds(180, 100, 230, 25);

        txtplayer_name = new TextField(pe.getPlayeremail());
        add(txtplayer_name);
        txtplayer_name.setBounds(180, 160, 230, 25);

        txtplayer_email = new TextField();
        add(txtplayer_email);
        txtplayer_email.setBounds(180, 220, 230, 25);

        txtpassword = new TextField();
        add(txtpassword);
        txtpassword.setBounds(180, 280, 230, 25);

        txtplayer_location = new TextField();
        add(txtplayer_location);
        txtplayer_location.setBounds(180, 340, 230, 25);

        btnupdate = new JButton("Update");
        add(btnupdate);
        btnupdate.setBounds(20, 480, 130, 25);
        btnupdate.addActionListener(new updateEvent());

        btndelete = new JButton("Delete");
        add(btndelete);
        btndelete.setBounds(170, 480, 130, 25);
        btndelete.addActionListener(new deleteEvent());

        btnback = new JButton("Back");
        add(btnback);
        btnback.setBounds(320, 480, 130, 25);
        btnback.addActionListener(new backEvent());

        setBounds(400, 30, 600, 550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            playerDetails();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerProfileUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerProfileUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public class updateEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Player pe = new Player();
            PlayerDAO pdao = new PlayerDAOImpl();
            pe.setPlayername(txtplayer_name.getText());
            pe.setPlayeremail(txtplayer_email.getText());
            pe.setPassword(txtpassword.getText());
            pe.setPlayerlocation(txtplayer_location.getText());

            try {
                pdao.update(pe);
                JOptionPane.showMessageDialog(rootPane, "Values Updated Successfully!");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PlayerProfileUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PlayerProfileUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class deleteEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Player pe = new Player();
            PlayerDAO pdao = new PlayerDAOImpl();
            pe.setPlayerid(Integer.parseInt(txtplayer_id.getText()));
            pe.setPlayername(txtplayer_name.getText());
            pe.setPlayeremail(txtplayer_email.getText());
            pe.setPassword(txtpassword.getText());
            pe.setPlayerlocation(txtplayer_location.getText());

            int confirm = JOptionPane.showConfirmDialog(rootPane, "All of your datas will be erased. Are you sure you want to delete your profile?");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    pdao.delete(pe);
                    clearField();
                    dispose();
                    LoginUI lg = new LoginUI();
                    lg.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PlayerProfileUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PlayerProfileUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public class backEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                PlayerUI pl = new PlayerUI();
                pl.SecondFrame("" + name);
                pl.setVisible(true);
                pl.setBounds(450, 50, 700, 650);
                dispose();
            } catch (SQLException | ClassNotFoundException e2) {
                System.out.println(e2.getMessage());
            }

        }

    }

    public void playerDetails() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Player entry = new Player();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        //JOptionPane.showMessageDialog(null, "Connection Successful");
        String sql = "Select * from player where player_email=?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        //stmt.setString(1, string);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            entry.setPlayerid(Integer.parseInt(rs.getString("player_id")));
            entry.setPlayername(rs.getString("player_name"));
            entry.setPlayeremail(rs.getString("player_email"));
            entry.setPassword(rs.getString("password"));
            entry.setPlayerlocation(rs.getString("player_location"));

        }

        conn.close();

        txtplayer_id.setText(Integer.toString(entry.getPlayerid()));
        txtplayer_name.setText(entry.getPlayername());
        txtplayer_email.setText(entry.getPlayeremail());
        txtpassword.setText(entry.getPassword());
        txtplayer_location.setText(entry.getPlayerlocation());

    }

    public void clearField() {
        txtplayer_id.setText("");
        txtplayer_name.setText("");
        txtplayer_email.setText("");
        txtpassword.setText("");
        txtplayer_location.setText("");

    }
}
