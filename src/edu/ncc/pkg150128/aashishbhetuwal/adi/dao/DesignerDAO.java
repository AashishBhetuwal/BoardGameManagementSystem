/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.dao;

import edu.ncc.pkg150128.aashishbhetuwal.adi.input.Designer;
import java.sql.SQLException;

/**
 *
 * @author Toshiba
 */
public interface DesignerDAO {

    Designer[] getAll();

    Designer getById(int id);

    public boolean insert(Designer de) throws ClassNotFoundException, SQLException;

    public boolean update(Designer de) throws ClassNotFoundException, SQLException;

    public boolean delete(Designer de) throws ClassNotFoundException, SQLException;

    public Designer findDesByName(String name) throws ClassNotFoundException, SQLException;

}
