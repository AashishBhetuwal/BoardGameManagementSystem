/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui;

import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.BookmarkDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.CategoryDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.DesignerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.BookmarkDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.CategoryDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.DesignerDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Bookmark;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Game;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

/**
 *
 * @author Toshiba
 */
public class GameDetails extends JFrame {

    private JLabel lblgamename, lblgamecategory, lblgamedesigner, lblminplayer, lbloptplayer, lblmaxplayer, lblavailabletime, lbltime;
    private TextField txtgameid, txtgamename, txtminplayer, txtoptplayer, txtmaxplayer;
    public static JComboBox<String> cmbgamecategory, cmbgamedesigner;
    private JButton btninsert, btnupdate, btndelete, btnsearch, btnback;
    JScrollBar scrolltime;
    CategoryDAO catDao;
    DesignerDAO designerDao;
    String gamename, username;
    private JList<String> bookmarkList;

    // List<Category> categorylist=new ArrayList<>();
    private ArrayList<String> game__category = new ArrayList<>();
    private ArrayList<String> game_designer = new ArrayList<>();

    public GameDetails(String gamename, String name) throws ClassNotFoundException, SQLException {
        this.gamename = gamename;
        this.username = name;

        category();
        designer();

        catDao = new CategoryDAOImpl();
        designerDao = new DesignerDAOImpl();
        JLabel lblintro = new JLabel("Games");
        add(lblintro);
        lblintro.setBounds(250, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        lblgamename = new JLabel("Game Title");
        add(lblgamename);
        lblgamename.setBounds(30, 200, 100, 40);

        txtgamename = new TextField();
        add(txtgamename);
        txtgamename.setBounds(180, 210, 150, 25);

        lblgamecategory = new JLabel("Game Category");
        add(lblgamecategory);
        lblgamecategory.setBounds(30, 250, 100, 40);

        cmbgamecategory = new JComboBox<>();
        for (String gamelist1 : game__category) {
            cmbgamecategory.addItem(gamelist1);

        }

        add(cmbgamecategory);
        cmbgamecategory.setBounds(180, 250, 150, 35);
        // cmbgamecategory.addActionListener(new cmbcategoryevent(null));

        lblgamedesigner = new JLabel("Game Designer");
        add(lblgamedesigner);
        lblgamedesigner.setBounds(30, 300, 100, 40);

        cmbgamedesigner = new JComboBox<>();
        for (String gamelist2 : game_designer) {
            cmbgamedesigner.addItem(gamelist2);
        }

        add(cmbgamedesigner);
        cmbgamedesigner.setBounds(180, 300, 150, 35);

        lblminplayer = new JLabel("Minimum Players");
        add(lblminplayer);
        lblminplayer.setBounds(30, 350, 100, 40);

        txtminplayer = new TextField();
        add(txtminplayer);
        txtminplayer.setBounds(180, 360, 150, 25);

        lbloptplayer = new JLabel("Optimum Players");
        add(lbloptplayer);
        lbloptplayer.setBounds(30, 400, 100, 40);

        txtoptplayer = new TextField();
        add(txtoptplayer);
        txtoptplayer.setBounds(180, 410, 150, 25);

        lblmaxplayer = new JLabel("Maximum Players");
        add(lblmaxplayer);
        lblmaxplayer.setBounds(30, 450, 150, 40);

        txtmaxplayer = new TextField();
        add(txtmaxplayer);
        txtmaxplayer.setBounds(180, 460, 150, 25);

        lblavailabletime = new JLabel("Available Time");
        add(lblavailabletime);
        lblavailabletime.setBounds(30, 500, 100, 40);

        lbltime = new JLabel("1");
        add(lbltime);
        lbltime.setBounds(180, 505, 40, 40);

        scrolltime = new JScrollBar();

        scrolltime.setOrientation(JScrollBar.VERTICAL);
        scrolltime.setBounds(230, 505, 40, 35);
        scrolltime.setMinimum(1);
        scrolltime.setMaximum(34);
        scrolltime.addAdjustmentListener(new GameDetails.Adjustment());

        add(scrolltime);

        btndelete = new JButton("Bookmark");
        add(btndelete);
        btndelete.setBounds(170, 600, 130, 25);
        btndelete.addActionListener(new GameDetails.bookmark());

        JButton btnback = new JButton("Back");
        add(btnback);
        btnback.setBounds(170, 630, 130, 25);
        btnback.addActionListener(new GameDetails.backEvent());

        setBounds(400, 30, 600, 700);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gamesearch();

    }

    private class bookmark implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Bookmark bk = new Bookmark();
                BookmarkDAO bkdao = new BookmarkDAOImpl();
                //gm.setGameid(Integer.parseInt(txtgameid.getText()));
                bk.setGamename(txtgamename.getText());

                String selectedGameDes = (String) cmbgamedesigner.getSelectedItem();
                Designer designerObj = designerDao.findDesByName(selectedGameDes);

                String gamedes = (String) cmbgamedesigner.getSelectedItem();
                DesignerDAOImpl desd = new DesignerDAOImpl();

                String selectedGameCat = (String) cmbgamecategory.getSelectedItem();
                Category catObj = catDao.findCategoryByName(selectedGameCat);
                String gamecat = (String) cmbgamecategory.getSelectedItem();

                //System.out.println(gamecat);
                CategoryDAOImpl catd = new CategoryDAOImpl();
                catd.findCategoryByName(gamecat);

                bk.setPlayername(username);
                bk.setMinplayer(Integer.parseInt(txtminplayer.getText()));
                bk.setOptplayer(Integer.parseInt(txtoptplayer.getText()));
                bk.setMaxplayer(Integer.parseInt(txtmaxplayer.getText()));
                bk.setAvailabletime(Integer.parseInt(lbltime.getText()));

                bkdao.insert(bk);
                JOptionPane.showMessageDialog(null, " Successfully Bookmarked!");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GameDetails.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class Adjustment implements AdjustmentListener {

        public void adjustmentValueChanged(AdjustmentEvent ae) {
            lbltime.setText("" + scrolltime.getValue());
        }
    }

    public class backEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                PlayerUI pl = new PlayerUI();
                pl.SecondFrame("" + username);
                pl.setVisible(true);

                pl.setBounds(450, 50, 700, 650);
                dispose();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameDetails.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GameDetails.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void gamesearch() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Game entry = new Game();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        //JOptionPane.showMessageDialog(null, "Connection Successful");
        String sql = "Select * from game where game_name=?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, gamename);
        //stmt.setString(1, string);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            entry.setGamename((rs.getString("game_name")));
            entry.setDesignerId(Integer.parseInt(rs.getString("designer_id")));
            entry.setGamecategoryId(Integer.parseInt(rs.getString("category_id")));
            entry.setMaxplayer(Integer.parseInt(rs.getString("maximum_player")));
            entry.setMinplayer(Integer.parseInt(rs.getString("minimum_player")));
            entry.setOptplayer(Integer.parseInt(rs.getString("optimal_player")));
            entry.setAvailabletime(Integer.parseInt(rs.getString("available_time")));

        }

        conn.close();

        txtgamename.setText((entry.getGamename()));
        txtmaxplayer.setText(Integer.toString(entry.getMaxplayer()));
        txtminplayer.setText(Integer.toString(entry.getMinplayer()));
        txtoptplayer.setText(Integer.toString(entry.getOptplayer()));
        lbltime.setText(Integer.toString(entry.getAvailabletime()));

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

            this.game_designer.add(rs.getString("designer_name"));

        }

    }

    public void category() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Category entry = new Category();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        //JOptionPane.showMessageDialog(null, "Connection Successful");
        String sql = "Select * from category";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            this.game__category.add(rs.getString("game_category"));

        }

        conn.close();

    }

}
