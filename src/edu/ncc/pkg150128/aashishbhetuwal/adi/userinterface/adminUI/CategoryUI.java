/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI;

import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.CategoryDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.CategoryDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.GameDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.PlayerUI;

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
public class CategoryUI extends JFrame {

    String designername;
    JLabel lblcategoryid, lblgamecategory, lblintro;
    TextField txtcategoryid, txtgamecategory;
    JButton btnupdate, btndelete, btninsert, btnsearch;

    public CategoryUI(String designername) {
        this.designername = designername;

        lblintro = new JLabel("Game Category");
        add(lblintro);
        lblintro.setBounds(200, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        lblcategoryid = new JLabel("Category ID");
        add(lblcategoryid);
        lblcategoryid.setBounds(30, 150, 100, 40);

        lblgamecategory = new JLabel("Game Category");
        add(lblgamecategory);
        lblgamecategory.setBounds(30, 220, 150, 40);

        txtcategoryid = new TextField();
        add(txtcategoryid);
        txtcategoryid.setBounds(180, 160, 150, 25);

        txtgamecategory = new TextField();
        add(txtgamecategory);
        txtgamecategory.setBounds(180, 230, 230, 25);

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

                CategoryDAO catdao = new CategoryDAOImpl();
                Category ce = new Category();
                ce.setCategoryid(Integer.parseInt(txtcategoryid.getText()));
                ce.setGamecategory(txtgamecategory.getText());

                catdao.update(ce);
                JOptionPane.showMessageDialog(rootPane, "Value updated Successfully");

            } catch (ClassNotFoundException cnfe) {
                System.out.println(cnfe.getMessage());
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());
            }

        }
    }

    public class deleteEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CategoryDAO catdao = new CategoryDAOImpl();
                Category ce = new Category();
                ce.setCategoryid(Integer.parseInt(txtcategoryid.getText()));
                ce.setGamecategory(txtgamecategory.getText());

                catdao.delete(ce);
                JOptionPane.showMessageDialog(rootPane, "Value Deleted Successfully");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CategoryUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class insertEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                CategoryDAO catdao = new CategoryDAOImpl();
                Category ce = new Category();
                ce.setCategoryid(Integer.parseInt(txtcategoryid.getText()));
                ce.setGamecategory(txtgamecategory.getText());

                catdao.insert(ce);
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
                Category entry = new Category();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
                //JOptionPane.showMessageDialog(null, "Connection Successful");
                String sql = "Select * from category where category_id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(txtcategoryid.getText()));

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {

                    entry.setGamecategory(rs.getString("game_category"));

                }

                conn.close();

                txtgamecategory.setText(entry.getGamecategory());

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
        txtcategoryid.setText("");
        txtgamecategory.setText("");
    }

}
