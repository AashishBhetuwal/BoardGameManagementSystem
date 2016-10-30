/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.CategoryDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.DesignerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.GameDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.CategoryDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.DesignerDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl.GameDAOImpl;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Game;
import static edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI.GameUI.cmbgamecategory;

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
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Toshiba
 */
public class GameUI extends JFrame {

    private JLabel lblgameid, lblgamename, lblgamecategory, lblgamedesigner, lblminplayer, lbloptplayer, lblmaxplayer, lblavailabletime, lbltime;
    private TextField txtgameid, txtgamename, txtminplayer, txtoptplayer, txtmaxplayer;
    public static JComboBox<String> cmbgamecategory, cmbgamedesigner;
    private JButton btninsert, btnupdate, btndelete, btnsearch, btnback;
    JScrollBar scrolltime;
    CategoryDAO catDao;
    DesignerDAO designerDao;

    private ArrayList<String> game__category = new ArrayList<>();
    private ArrayList<String> game_designer = new ArrayList<>();

    public GameUI() throws ClassNotFoundException, SQLException {

        category();
        designer();
        catDao = new CategoryDAOImpl();
        designerDao = new DesignerDAOImpl();
        JLabel lblintro = new JLabel("Games");
        add(lblintro);
        lblintro.setBounds(250, 30, 200, 50);
        lblintro.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N

        lblintro.setForeground(new java.awt.Color(153, 153, 0));

        lblgameid = new JLabel("Game ID");
        add(lblgameid);
        lblgameid.setBounds(30, 150, 100, 40);

        txtgameid = new TextField("Only for Search");
        add(txtgameid);
        txtgameid.setBounds(180, 160, 150, 25);

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
        scrolltime.addAdjustmentListener(new Adjustment());

        add(scrolltime);

        btnsearch = new JButton("Search");
        add(btnsearch);
        btnsearch.setBounds(350, 160, 130, 25);
        btnsearch.addActionListener(new searchEvent());

        btnupdate = new JButton("Update");
        add(btnupdate);
        btnupdate.setBounds(20, 600, 130, 25);
        btnupdate.addActionListener(new updateEvent());

        btndelete = new JButton("Delete");
        add(btndelete);
        btndelete.setBounds(170, 600, 130, 25);
        btndelete.addActionListener(new deleteEvent());

        btninsert = new JButton("Insert");
        add(btninsert);
        btninsert.setBounds(320, 600, 130, 25);
        btninsert.addActionListener(new insertEvent());

        JButton btnback = new JButton("Back");
        add(btnback);
        btnback.setBounds(170, 630, 130, 25);
        btnback.addActionListener(new backEvent());

        setBounds(400, 30, 600, 700);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public class searchEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                Game gm = new Game();
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
                //JOptionPane.showMessageDialog(null, "Connection Successful");
                String sql = "Select * from game where game_id=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(txtgameid.getText()));

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {

                    gm.setGamename(rs.getString("game_name"));

                    gm.setGamecategoryId(Integer.parseInt(rs.getString("category_id")));
                    gm.setDesignerId(Integer.parseInt(rs.getString("designer_id")));
                    gm.setMinplayer(Integer.parseInt(rs.getString("minimum_player")));
                    gm.setMaxplayer(Integer.parseInt(rs.getString("maximum_player")));
                    gm.setOptplayer(Integer.parseInt(rs.getString("optimal_player")));
                    gm.setAvailabletime(Integer.parseInt(rs.getString("available_time")));

                }

                System.out.println(gm.getGamename());

                conn.close();
                txtgamename.setText(gm.getGamename());
                txtmaxplayer.setText(Integer.toString(gm.getMaxplayer()));
                txtminplayer.setText(Integer.toString(gm.getMinplayer()));
                txtoptplayer.setText(Integer.toString(gm.getOptplayer()));

                cmbgamedesigner.setSelectedItem(gm.getGamecategoryId());

                cmbgamecategory.setSelectedItem(gm.getGamecategoryId());
                lbltime.setText(Integer.toString(gm.getAvailabletime()));

            } catch (SQLException | ClassNotFoundException sc) {
                System.out.println(sc.getMessage());
            }
        }
    }

    public class Adjustment implements AdjustmentListener {

        public void adjustmentValueChanged(AdjustmentEvent ae) {
            lbltime.setText("" + scrolltime.getValue());
        }
    }

    public class updateEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameDAO gamedao = new GameDAOImpl(null, null);
            Game game = new Game();
            try {
                game.setGameid(Integer.parseInt(txtgameid.getText()));
                game.setGamename(txtgamename.getText());

                String selectedGameDes = (String) cmbgamedesigner.getSelectedItem();
                Designer designerObj = designerDao.findDesByName(selectedGameDes);
                game.setDesignerId(designerObj.getDesignerid());

                String selectedGameCat = (String) cmbgamecategory.getSelectedItem();
                Category catObj = catDao.findCategoryByName(selectedGameCat);
                game.setGamecategoryId(catObj.getCategoryid());

                game.setMaxplayer(Integer.parseInt(txtmaxplayer.getText()));
                game.setMinplayer(Integer.parseInt(txtminplayer.getText()));
                game.setOptplayer(Integer.parseInt(txtoptplayer.getText()));
                game.setAvailabletime(Integer.parseInt(lbltime.getText()));

                gamedao.update(game);

                JOptionPane.showMessageDialog(rootPane, "Value Updated Successfully");
//                clearField();
            } catch (SQLException | ClassNotFoundException sc) {
                System.out.println(sc.getMessage());
            }

        }
    }

    public class deleteEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameDAO gamedao = new GameDAOImpl(null, null);
            Game game = new Game();

            try {
                game.setGameid(Integer.parseInt(txtgameid.getText()));
                game.setGamename(txtgamename.getText());

                String selectedGameDes = (String) cmbgamedesigner.getSelectedItem();
                Designer designerObj = designerDao.findDesByName(selectedGameDes);
                game.setDesignerId(designerObj.getDesignerid());

                String selectedGameCat = (String) cmbgamecategory.getSelectedItem();
                Category catObj = catDao.findCategoryByName(selectedGameCat);
                game.setGamecategoryId(catObj.getCategoryid());

                game.setMaxplayer(Integer.parseInt(txtmaxplayer.getText()));
                game.setMinplayer(Integer.parseInt(txtminplayer.getText()));
                game.setOptplayer(Integer.parseInt(txtoptplayer.getText()));
                game.setAvailabletime(Integer.parseInt(lbltime.getText()));

                gamedao.delete(game);

                JOptionPane.showMessageDialog(rootPane, "Value Deleted Successfully");
                clearField();
                txtgameid.setText("");
            } catch (SQLException | ClassNotFoundException sc) {
                System.out.println(sc.getMessage());
            }
        }
    }

    public class insertEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Game gm = new Game();
                GameDAO gdao = new GameDAOImpl(null, null);
                //gm.setGameid(Integer.parseInt(txtgameid.getText()));
                gm.setGamename(txtgamename.getText());

                String selectedGameDes = (String) cmbgamedesigner.getSelectedItem();
                Designer designerObj = designerDao.findDesByName(selectedGameDes);
                gm.setDesignerId(designerObj.getDesignerid());

                String gamedes = (String) cmbgamedesigner.getSelectedItem();
                DesignerDAOImpl desd = new DesignerDAOImpl();
                desd.findDesByName(gamedes);

                String selectedGameCat = (String) cmbgamecategory.getSelectedItem();
                Category catObj = catDao.findCategoryByName(selectedGameCat);
                gm.setGamecategoryId(catObj.getCategoryid());
                String gamecat = (String) cmbgamecategory.getSelectedItem();

                //CategoryDAOImpl catd=new CategoryDAOImpl(gamecat);
                //System.out.println(gamecat);
                CategoryDAOImpl catd = new CategoryDAOImpl();
                catd.findCategoryByName(gamecat);

                gm.setMinplayer(Integer.parseInt(txtminplayer.getText()));
                gm.setOptplayer(Integer.parseInt(txtoptplayer.getText()));
                gm.setMaxplayer(Integer.parseInt(txtmaxplayer.getText()));
                gm.setAvailabletime(Integer.parseInt(lbltime.getText()));
                gdao.insert(gm);
                clearField();
                JOptionPane.showMessageDialog(rootPane, "Value Inserted Successfully!");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
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
//            game__category.add(Integer.toString(entry.getCategoryid()));
//            game_designer.add(entry.getGamecategory());

        }

    }

    public void clearField() {
//        txtgameid.setText("");
        txtgamename.setText("");
        txtminplayer.setText("");
        txtmaxplayer.setText("");
        txtoptplayer.setText("");
        lbltime.setText("1");

    }

}
