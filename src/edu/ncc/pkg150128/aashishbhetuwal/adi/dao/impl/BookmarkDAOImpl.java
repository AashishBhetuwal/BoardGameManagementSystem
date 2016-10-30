/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.BookmarkDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Bookmark;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class BookmarkDAOImpl implements BookmarkDAO {

    @Override
    public boolean insert(Bookmark bk) throws ClassNotFoundException, SQLException, MySQLIntegrityConstraintViolationException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);

        String sql = "insert into bookmark(game_name,minimum_player,maximum_player,optimal_player,available_time,player_name) values(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

      
        ps.setString(1, bk.getGamename());

        ps.setInt(2, bk.getMinplayer());
        ps.setInt(3, bk.getMaxplayer());
        ps.setInt(4, bk.getOptplayer());

        ps.setInt(5, bk.getAvailabletime());
        ps.setString(6, bk.getPlayername());
        ps.executeUpdate();
        connection.close();
        ps.close();

        return true;
    }

    @Override
    public boolean delete(Bookmark bk) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        String sql = "delete from bookmark where game_name=?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, bk.getGamename());
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;
    }

}
