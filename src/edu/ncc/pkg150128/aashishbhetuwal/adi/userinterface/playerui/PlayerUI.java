/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui;


import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Bookmark;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Game;
import edu.ncc.pkg150128.aashishbhetuwal.adi.mainui.LoginUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Toshiba
 */
public class PlayerUI extends JFrame {

    JPanel firstpanel;
    secondpanel sp;
    thirdPanel tp;
    JLabel lblname, lblbookmark, lblavailablegames, lbldesigner, lblgame_name, lblgamecategory, lbltime, lblplayers;
    JButton btnshow, btnlogout, btnplayerprofile, btnexit;
    TextField txttime, txtplayers, txtgame_name;
    public static JComboBox<String> cmbgamecategory, cmbdesigner;
    JScrollBar scrolltime;
    String name, name1, Gamename, Gamename1;
    String txtgame_category;
    String txtcategoryid;
    private JList<String> gameList;
    private JList<String> bookmarkList;

    private ArrayList<String> game__category = new ArrayList<>();
    private ArrayList<String> game_designer = new ArrayList<>();
    boolean bookmark;

    /**
     *
     * @param asd
     */
    public PlayerUI() throws ClassNotFoundException, SQLException {

        database();
        designer();

    }

    public void SecondFrame(String name) {

        this.name = name;

        name1 = name;

        firstpanel = new JPanel();
        //jpanel body

        firstpanel.setBackground(Color.LIGHT_GRAY);
        firstpanel.setPreferredSize(new Dimension(150, 400));
        add(firstpanel, BorderLayout.LINE_START);
        firstpanel.setLayout(null);

        sp = new secondpanel();
        sp.setBackground(Color.WHITE);
        sp.setPreferredSize(new Dimension(150, 400));
        add(sp, BorderLayout.CENTER);
        sp.setLayout(null);

        tp = new thirdPanel();
        tp.setBackground(Color.LIGHT_GRAY);
        tp.setPreferredSize(new Dimension(200, 400));
        add(tp, BorderLayout.LINE_END);

        //labels    
        lblname = new JLabel("Welcome " + name);
        lblname.setBounds(120, 10, 150, 30);
        sp.add(lblname);

        lblname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        lblname.setForeground(new java.awt.Color(153, 153, 0));

        lbltime = new JLabel("Preferred Time");
        lbltime.setBounds(20, 240, 150, 30);
        sp.add(lbltime);

        lbltime = new JLabel("1");
        lbltime.setBounds(180, 240, 30, 30);
        sp.add(lbltime);

        //scroll bar time
        scrolltime = new JScrollBar();

        scrolltime.setOrientation(JScrollBar.VERTICAL);
        scrolltime.setBounds(230, 240, 40, 30);
        scrolltime.setMinimum(1);
        scrolltime.setMaximum(34);
        scrolltime.addAdjustmentListener(new Adjustment());

        sp.add(scrolltime);

        lblplayers = new JLabel("Preferred Players");
        lblplayers.setBounds(20, 290, 150, 30);
        sp.add(lblplayers);

        txtplayers = new TextField();
        txtplayers.setBounds(180, 290, 150, 30);
        sp.add(txtplayers);

        lblgame_name = new JLabel("Game Title Preference");
        lblgame_name.setBounds(20, 340, 150, 30);
        sp.add(lblgame_name);

        txtgame_name = new TextField();
        txtgame_name.setBounds(180, 340, 150, 30);
        sp.add(txtgame_name);

        //firstpanel
        lblbookmark = new JLabel("Bookmarks");
        lblbookmark.setBounds(20, 0, 150, 30);
        firstpanel.add(lblbookmark);

        lblbookmark.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        lblbookmark.setForeground(new java.awt.Color(50, 153, 100));

        lblavailablegames = new JLabel("Available Games");
        lblavailablegames.setBounds(20, 20, 150, 30);
        tp.add(lblavailablegames);
        lblavailablegames.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        lblavailablegames.setForeground(new java.awt.Color(50, 153, 100));

//        //button
        btnplayerprofile = new JButton("Player Profile");
        sp.add(btnplayerprofile);
        btnplayerprofile.setBounds(10, 60, 310, 30);
        btnplayerprofile.addActionListener(new playerprofileEvent());

        btnshow = new JButton("Show Games");
        sp.add(btnshow);
        btnshow.setBounds(180, 450, 150, 35);
        btnshow.addActionListener(new showEvent());

        btnlogout = new JButton("Log Out");
        sp.add(btnlogout);
        btnlogout.setBounds(10, 450, 150, 35);
        btnlogout.addActionListener(new logoutEvent());

        btnexit = new JButton("Exit");
        sp.add(btnexit);
        btnexit.setBounds(100, 520, 150, 35);
        btnexit.addActionListener(new ExitEvent());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        firstpanel();

    }

    public void firstpanel() {

        //this.Gamename = gamename;
        //this.Gamename1=gamename1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Bookmark entry = new Bookmark();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
            //JOptionPane.showMessageDialog(null, "Connection Successful");
            String sql = "Select * from bookmark where player_name=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                entry.setGamename(rs.getString("game_name"));
                entry.setMaxplayer(Integer.parseInt(rs.getString("maximum_player")));
                entry.setMinplayer(Integer.parseInt(rs.getString("minimum_player")));
                entry.setOptplayer(Integer.parseInt(rs.getString("optimal_player")));
                entry.setAvailabletime(Integer.parseInt(rs.getString("available_time")));
            }
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement(entry.getGamename());
            bookmarkList = new JList<>(listModel);
            firstpanel.add(bookmarkList);

            bookmarkList.setBounds(5, 50, 140, 160);

            bookmarkList.setVisible(true);

            bookmarkList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        final List<String> selectedValuesList = bookmarkList.getSelectedValuesList();

                        try {
                            BookmarkDetails gm = new BookmarkDetails("" + name);
                            dispose();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            });

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

    }

    private static class secondpanel extends JPanel {

    }

    private static class thirdPanel extends JPanel {

//        DefaultListModel<String> model = new DefaultListModel<>();
//        JList<String> list = new JList<>(model);
    }

    public class Adjustment implements AdjustmentListener {

        public void adjustmentValueChanged(AdjustmentEvent ae) {
            lbltime.setText("" + scrolltime.getValue());
        }
    }

    public class showEvent implements ActionListener {

        @Override
        @SuppressWarnings("IncompatibleEquals")
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Game gm = new Game();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
                //JOptionPane.showMessageDialog(null, "Connection Successful");
                String sql = "Select * from game where game_name=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtgame_name.getText());
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    gm.setGamename(rs.getString("game_name"));

                    gm.setGamecategoryId(Integer.parseInt(rs.getString("category_id")));
                    gm.setDesignerId(Integer.parseInt(rs.getString("designer_id")));
                    gm.setMinplayer(Integer.parseInt(rs.getString("minimum_player")));
                    gm.setMaxplayer(Integer.parseInt(rs.getString("maximum_player")));
                    gm.setOptplayer(Integer.parseInt(rs.getString("optimal_player")));
                    gm.setAvailabletime(Integer.parseInt(rs.getString("available_time")));

                }

                conn.close();

                String availabletime, preferredplayers1, preferredplayers2, preferredplayers3;
                availabletime = String.valueOf(gm.getAvailabletime());

                preferredplayers1 = String.valueOf(gm.getMaxplayer());

                preferredplayers2 = String.valueOf(gm.getMinplayer());
                preferredplayers3 = String.valueOf(gm.getOptplayer());

                if (txtgame_name.getText().equalsIgnoreCase(gm.getGamename()) && lbltime.getText().equalsIgnoreCase(availabletime) && txtplayers.getText().equalsIgnoreCase(preferredplayers1) || txtplayers.getText().equalsIgnoreCase(preferredplayers2) || txtplayers.getText().equalsIgnoreCase(preferredplayers3)) {
                    System.out.println("game matched");
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addElement(txtgame_name.getText());

                    gameList = new JList<>(listModel);
                    tp.add(gameList);
                    gameList.setBounds(10, 70, 200, 160);

                    gameList.setVisible(true);

                    gameList.addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            if (!e.getValueIsAdjusting()) {
                                final List<String> selectedValuesList = gameList.getSelectedValuesList();

                                try {
                                    GameDetails gm = new GameDetails("" + txtgame_name.getText(), name);
                                    dispose();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(PlayerUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                    });

                } else {
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    listModel.addElement("Game Not Found!");

                    gameList = new JList<>(listModel);
                    tp.add(gameList);
                    gameList.setBounds(10, 70, 200, 160);

                    gameList.setVisible(true);

                }

            } catch (SQLException | ClassNotFoundException sc) {
                System.out.println(sc.getMessage());
            }
        }

    }

    public class logoutEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int a = JOptionPane.showConfirmDialog(firstpanel, "Are you sure?");
            if (a == JOptionPane.YES_OPTION) {
                dispose();
                LoginUI lg = new LoginUI();

            }
        }

    }

    public class playerprofileEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            PlayerProfileUI pl = new PlayerProfileUI(name);
            pl.setVisible(true);

            dispose();

        }

    }

    public class ExitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Do you want to exit?");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        }

    }

    public void database() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Category entry = new Category();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        //JOptionPane.showMessageDialog(null, "Connection Successful");
        String sql = "Select * from category";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            this.game__category.add(rs.getString(2));

        }

        conn.close();

    }

    public void designer() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Category entry = new Category();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        //JOptionPane.showMessageDialog(null, "Connection Successful");
        String sql = "Select * from game_designer";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            this.game_designer.add(rs.getString(2));

        }

        conn.close();

    }
}
