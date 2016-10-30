/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.GameDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class GameDAOImpl implements GameDAO {

    String designername;
    String gamecategory;
    private Game[] GameList = new Game[10];
    private List<Category> categorylist = new ArrayList<>();
    int counter = 0;
    private ArrayList<String> game_designer = new ArrayList<>();

    public GameDAOImpl(String designername, String gamecategory) {
        this.designername = designername;
        this.gamecategory = gamecategory;
    }

    public boolean insert(Game ge) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        JOptionPane.showMessageDialog(null, "Connection Successful!");

        String sql = "insert into game(game_name,designer_id,minimum_player,maximum_player,optimal_player,category_id,available_time) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ge.getGamename());
        ps.setInt(2, ge.getDesignerId());
        ps.setInt(3, ge.getMinplayer());
        ps.setInt(4, ge.getMaxplayer());
        ps.setInt(5, ge.getOptplayer());
        ps.setInt(6, ge.getGamecategoryId());
        ps.setInt(7, ge.getAvailabletime());
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;

    }

    @Override
    public boolean update(Game ge) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);

        String sql = "Update game set game_name=?,category_id=?,designer_id=?,minimum_player=?,maximum_player=?,optimal_player=?,available_time=? where game_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, ge.getGamename());
        ps.setInt(2, ge.getGamecategoryId());
        ps.setInt(3, ge.getDesignerId());
        ps.setInt(4, ge.getMinplayer());
        ps.setInt(5, ge.getMaxplayer());
        ps.setInt(6, ge.getOptplayer());
        ps.setInt(7, ge.getAvailabletime());
        ps.setInt(8, ge.getGameid());

        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;

    }

    @Override
    public boolean delete(Game ge) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        String sql = "delete from game where game_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ge.getGameid());
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;
    }

    @Override
    public Game getById(int id) {
        for (int i = 0; i < GameList.length; i++) {
            Game g = GameList[i];
            if (g != null && g.getGameid() == id) {
                return g;
            }
        }
        return null;
    }

    @Override
    public List<Category> getall() {
        return categorylist;
    }

}
