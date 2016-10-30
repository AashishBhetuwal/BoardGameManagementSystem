/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI;

import edu.ncc.pkg150128.aashishbhetuwal.adi.mainui.LoginUI;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.PlayerUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class AdminUI extends JFrame {

    JLabel lblintro;
    JButton btngamecategory, btngamedesigner, btngames, btnplayers, btnback, btnexit;

    public AdminUI() {
        lblintro = new JLabel("Welcome Admin");
        add(lblintro);
        lblintro.setBounds(200, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        btngamecategory = new JButton("Game Category");
        add(btngamecategory);
        btngamecategory.setBounds(90, 150, 400, 30);
        btngamecategory.addActionListener(new categoryEvent());

        btngamedesigner = new JButton("Game Designer");
        add(btngamedesigner);
        btngamedesigner.setBounds(90, 200, 400, 30);
        btngamedesigner.addActionListener(new designerEvent());

        btngames = new JButton("Games");
        add(btngames);
        btngames.setBounds(90, 250, 400, 30);
        btngames.addActionListener(new gamesEvent());

        btnplayers = new JButton("Players");
        add(btnplayers);
        btnplayers.setBounds(90, 300, 400, 30);
        btnplayers.addActionListener(new playersEvent());

        btnback = new JButton("LogOut");
        add(btnback);
        btnback.setBounds(90, 400, 150, 30);
        btnback.addActionListener(new logoutEvent());

        btnexit = new JButton("Exit");
        add(btnexit);
        btnexit.setBounds(340, 400, 150, 30);
        btnexit.addActionListener(new closeEvent());

        setBounds(400, 30, 600, 550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public class categoryEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CategoryUI gc = new CategoryUI(null);
            dispose();

        }

    }

    public class designerEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DesignerUI gd = new DesignerUI();
            dispose();
        }

    }

    public class gamesEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                GameUI gm = new GameUI();
                gm.setVisible(true);
                dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public class playersEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            AdminPlayerUI pl = new AdminPlayerUI();
            pl.setVisible(true);
            dispose();

        }

    }

    public class logoutEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int a = JOptionPane.showConfirmDialog(rootPane, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {

                LoginUI lg = new LoginUI();
                dispose();

            }
        }

    }

    public class closeEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure?");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        }

    }
}
