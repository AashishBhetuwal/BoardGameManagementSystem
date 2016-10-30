/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl;


import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.DesignerDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class DesignerDAOImpl implements DesignerDAO{
  private Designer[] designerList=new Designer[10];
  String gamedes;
  
int counter = 0;
    @Override
    public boolean insert(Designer de) throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        JOptionPane.showMessageDialog(null, "Connection Successful!");

        String sql = "insert into game_designer(designer_id,designer_name,designer_email,designed_games) values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
       ps.setInt(1,de.getDesignerid());
        ps.setString(2, de.getDesignername());
        ps.setString(3,de.getDesigneremail());
        ps.setString(4, de.getDesignedgames());
        
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;

    }

    @Override
    public boolean update(Designer de) throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
       
        String sql = "Update game_designer set designer_name=?,designer_email=?,designed_games=? where designer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
       
        ps.setString(1, de.getDesignername());
        ps.setString(2,de.getDesigneremail());
        ps.setString(3, de.getDesignedgames());
        ps.setInt(4, de.getDesignerid());
        
        
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;

    }

    @Override
    public boolean delete(Designer de) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
       

        String sql = "delete from game_designer where designer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
       
        ps.setInt(1, de.getDesignerid());
       
        
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;

    }

    @Override
    public Designer[] getAll() {
        return designerList;
    }

    @Override
    public Designer getById(int id) {
        for (int i = 0; i < designerList.length; i++) {
            Designer g = designerList[i];
            if (g != null && g.getDesignerid()== id) {
                return g;
            }
        }
            return null;
    }

    @Override
    public Designer findDesByName(String name) throws ClassNotFoundException, SQLException {
        this.gamedes=name;
        System.out.println(gamedes);
        Designer desObj = new Designer();
         Class.forName("com.mysql.jdbc.Driver");
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        //JOptionPane.showMessageDialog(null, "Connection Successful");
        String sql = "Select * from game_designer where designer_name=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, gamedes);
        ResultSet rs = stmt.executeQuery();
        
        
        while (rs.next()) {            
            desObj=new Designer();
            desObj.setDesignerid(rs.getInt("designer_id"));
          
           
        }
        return desObj;
    }

  
    
}
