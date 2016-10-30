/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl;


import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.PlayerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */

public class PlayerDAOImpl implements PlayerDAO{
private Player[] playerList=new Player[10];
int counter = 0;

    @Override
    public boolean insert(Player pe) throws ClassNotFoundException, SQLException {
       Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
      

        String sql = "insert into player(player_name,player_email,password,player_location) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
       
        ps.setString(1,pe.getPlayername());
        ps.setString(2,pe.getPlayeremail());
        ps.setString(3,pe.getPassword());
        ps.setString(4, pe.getPlayerlocation());
        ps.executeUpdate();
        connection.close();
        ps.close();

        return true;

    }

    @Override
    public boolean update(Player pe) throws ClassNotFoundException, SQLException {
 Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        
     String sql = "Update player set player_name=?,player_email=?,password=?,player_location=? where player_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);
      
        ps.setString(1,pe.getPlayername());
        ps.setString(2,pe.getPlayeremail());
        ps.setString(3,pe.getPassword());
        ps.setString(4, pe.getPlayerlocation());
        ps.setInt(5, pe.getPlayerid());
        ps.executeUpdate();
        connection.close();
        ps.close();
        
        return true;
    }

    @Override
    public boolean delete(Player pe) throws ClassNotFoundException, SQLException {
 Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        
     String sql = "delete from player where player_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);
      
        ps.setInt(1,pe.getPlayerid());
       
        ps.executeUpdate();
        connection.close();
        ps.close();

        return true;
 
    }

    @Override
    public Player[] getAll() {
        return playerList;
    }

    @Override
    public Player getById(int id) {
         for (int i = 0; i < playerList.length; i++) {
            Player p = playerList[i];
            if (p != null && p.getPlayerid()== id) {
                return p;
            }
        }
            return null;
    }

    
}
