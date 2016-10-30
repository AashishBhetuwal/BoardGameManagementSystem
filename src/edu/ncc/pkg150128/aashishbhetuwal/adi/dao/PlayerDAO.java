/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao;

import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Player;
import java.sql.SQLException;

/**
 *
 * @author Toshiba
 */
public interface PlayerDAO {

    public boolean insert(Player pe) throws ClassNotFoundException, SQLException;

    public boolean update(Player pe) throws ClassNotFoundException, SQLException;

    public boolean delete(Player pe) throws ClassNotFoundException, SQLException;

    Player[] getAll();

    Player getById(int id);

}
