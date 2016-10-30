/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI;

import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.DesignerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.DesignerDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.GameDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class DesignerUI extends JFrame {

    JLabel lbldesignerid, lbldesignername, lblintro, lbldesignedgames, lbldesigneremail;
    TextField txtdesignerid, txtdesignername, txtdesigneremail, txtdesignedgames;
    JButton btnupdate, btndelete, btninsert, btnsearch;

    public DesignerUI() {
        lblintro = new JLabel("Game Designer");
        add(lblintro);
        lblintro.setBounds(200, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        lbldesignerid = new JLabel("Designer ID");
        add(lbldesignerid);
        lbldesignerid.setBounds(30, 150, 100, 40);

        lbldesignername = new JLabel("Designer Name");
        add(lbldesignername);
        lbldesignername.setBounds(30, 220, 150, 40);

        lbldesigneremail = new JLabel("Designer Email");
        add(lbldesigneremail);
        lbldesigneremail.setBounds(30, 260, 150, 40);

        lbldesignedgames = new JLabel("Designed Games");
        add(lbldesignedgames);
        lbldesignedgames.setBounds(30, 300, 150, 40);

        txtdesignerid = new TextField();
        add(txtdesignerid);
        txtdesignerid.setBounds(180, 160, 150, 25);

        txtdesignername = new TextField();
        add(txtdesignername);
        txtdesignername.setBounds(180, 230, 230, 25);

        txtdesigneremail = new TextField();
        add(txtdesigneremail);
        txtdesigneremail.setBounds(180, 270, 230, 25);

        txtdesignedgames = new TextField();
        add(txtdesignedgames);
        txtdesignedgames.setBounds(180, 310, 230, 25);

        btnupdate = new JButton("Update");
        add(btnupdate);
        btnupdate.setBounds(20, 380, 130, 25);
        btnupdate.addActionListener(new updateEvent());

        btndelete = new JButton("Delete");
        add(btndelete);
        btndelete.setBounds(170, 380, 130, 25);
        btndelete.addActionListener(new deleteEvent());

        btninsert = new JButton("Insert");
        add(btninsert);
        btninsert.setBounds(320, 380, 130, 25);
        btninsert.addActionListener(new insertEvent());

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

    public class updateEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                DesignerDAO dedao = new DesignerDAOImpl();
                Designer de = new Designer();
                de.setDesignerid(Integer.parseInt(txtdesignerid.getText()));
                de.setDesignername(txtdesignername.getText());
                de.setDesigneremail(txtdesigneremail.getText());
                de.setDesignedgames(txtdesignedgames.getText());

                dedao.update(de);
                JOptionPane.showMessageDialog(rootPane, "Value Updated Successfully");
               // clearField();

            } catch (ClassNotFoundException cs) {
                System.out.println(cs.getMessage());

            } catch (SQLException se) {
                System.out.println(se.getMessage());

            }

        }
    }

    public class deleteEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DesignerDAO ddao = new DesignerDAOImpl();
                Designer de = new Designer();
                de.setDesignerid(Integer.parseInt(txtdesignerid.getText()));
                de.setDesignername(txtdesignername.getText());
                de.setDesigneremail(txtdesigneremail.getText());
                de.setDesignedgames(txtdesignedgames.getText());

                ddao.delete(de);
                JOptionPane.showMessageDialog(rootPane, "Value Deleted Successfully");
                clearField();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DesignerUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DesignerUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class insertEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                DesignerDAO dedao = new DesignerDAOImpl();
                Designer de = new Designer();
                de.setDesignerid(Integer.parseInt(txtdesignerid.getText()));
                de.setDesignername(txtdesignername.getText());
                de.setDesigneremail(txtdesigneremail.getText());

                de.setDesignedgames(txtdesignedgames.getText());

                dedao.insert(de);
                JOptionPane.showMessageDialog(rootPane, "Value Inserted Successfully");
                clearField();

            } catch (ClassNotFoundException cs) {
                System.out.println(cs.getMessage());

            } catch (SQLException se) {
                System.out.println(se.getMessage());

            }

        }
    }

    public class searchEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Designer de = new Designer();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
                //JOptionPane.showMessageDialog(null, "Connection Successful");
                String sql = "Select * from game_designer where designer_id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(txtdesignerid.getText()));

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {

                    de.setDesignername(rs.getString("designer_name"));
                    de.setDesigneremail(rs.getString("designer_email"));
                    de.setDesignedgames(rs.getString("designed_games"));

                }

                conn.close();

                txtdesignername.setText(de.getDesignername());
                txtdesigneremail.setText(de.getDesigneremail());
                txtdesignedgames.setText(de.getDesignedgames());

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

    public void clearField() {
        txtdesignerid.setText("");
        txtdesignername.setText("");
        txtdesigneremail.setText("");
        txtdesignedgames.setText("");
    }

}
