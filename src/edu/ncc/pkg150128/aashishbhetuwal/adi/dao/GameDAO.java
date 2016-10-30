/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Category;
import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Game;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Toshiba
 */
public interface GameDAO {

    List<Category> getall();

    Game getById(int id);

    public boolean insert(Game ge) throws ClassNotFoundException, SQLException;

    public boolean update(Game ge) throws ClassNotFoundException, SQLException;

    public boolean delete(Game ge) throws ClassNotFoundException, SQLException;

}
