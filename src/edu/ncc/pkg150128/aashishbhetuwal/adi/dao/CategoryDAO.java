/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao;

import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import java.sql.SQLException;

/**
 *
 * @author Toshiba
 */
public interface CategoryDAO {

    Category[] getAll();

    Category getById(int id);

    public boolean insert(Category ce) throws ClassNotFoundException, SQLException;

    public boolean update(Category ce) throws ClassNotFoundException, SQLException;

    public boolean delete(Category ce) throws ClassNotFoundException, SQLException;

    public Category findCategoryByName(String catName) throws ClassNotFoundException, SQLException;

}
