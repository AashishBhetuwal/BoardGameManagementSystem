/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao;

import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Bookmark;
import java.sql.SQLException;

/**
 *
 * @author Toshiba
 */
public interface BookmarkDAO {

    public boolean insert(Bookmark bk) throws ClassNotFoundException, SQLException, com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

    public boolean delete(Bookmark bk) throws ClassNotFoundException, SQLException;

}
