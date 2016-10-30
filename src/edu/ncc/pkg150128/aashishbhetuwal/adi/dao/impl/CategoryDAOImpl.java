/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao.impl;

import edu.ncc.pkg150128.aashishbhetuwal.adi.dao.CategoryDAO;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Toshiba
 */
public class CategoryDAOImpl implements CategoryDAO {

    private Category[] categoryList = new Category[10];
    int counter = 0;
    String catname;

    @Override
    public boolean insert(Category ce) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        JOptionPane.showMessageDialog(null, "Connection Successful!");

        String sql = "insert into category(category_id,game_category) values(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, ce.getCategoryid());
        ps.setString(2, ce.getGamecategory());
        ps.executeUpdate();
        connection.close();
        ps.close();

        return true;
    }

    @Override
    public boolean update(Category ce) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        String sql = "Update category set game_category=? where category_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ce.getGamecategory());
        ps.setInt(2, ce.getCategoryid());
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;
    }

    @Override
    public boolean delete(Category ce) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
        String sql = "delete from category where category_id=?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, ce.getCategoryid());
        ps.executeUpdate();
        connection.close();
        ps.close();
        return true;
    }

    @Override
    public Category[] getAll() {
        return categoryList;
    }

    @Override
    public Category getById(int id) {
        for (int i = 0; i < categoryList.length; i++) {
            Category g = categoryList[i];
            if (g != null && g.getCategoryid() == id) {
                return g;
            }
        }
        return null;
    }

    @Override
    public Category findCategoryByName(String catName) throws ClassNotFoundException, SQLException {

        this.catname = catName;
        Category catObj = new Category();

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/adi", "root", null);
       
        String sql = "Select * from category where game_category=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

       
        stmt.setString(1, catname);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            catObj = new Category();
            catObj.setCategoryid(rs.getInt("category_id"));
        }
        conn.close();

     
        return catObj;

    }

}
