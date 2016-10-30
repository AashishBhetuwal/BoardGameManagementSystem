/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi;

import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.PlayerProfileUI;
import edu.ncc.pkg150128.aashishbhetuwal.adi.mainui.LoginUI;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.adminUI.GameUI;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.GameDetails;
import edu.ncc.pkg150128.aashishbhetuwal.adi.userinterface.playerui.PlayerUI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toshiba
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            // TODO code application logic here
        LoginUI lg = new LoginUI();
        lg.setVisible(true);

    }

}
