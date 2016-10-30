/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.input;

/**
 *
 * @author Toshiba
 */
public class Designer {

    private int designerid;
    private String designername, designeremail, designedgames;

    public Designer() {
    }

    public Designer(String designername, String designeremail, String designedgames) {
        this.designername = designername;
        this.designeremail = designeremail;
        this.designedgames = designedgames;
    }

    public Designer(int designerid) {
        this.designerid = designerid;
    }

    public int getDesignerid() {
        return designerid;
    }

    public void setDesignerid(int designerid) {
        this.designerid = designerid;
    }

    public String getDesignername() {
        return designername;
    }

    public void setDesignername(String designername) {
        this.designername = designername;
    }

    public String getDesigneremail() {
        return designeremail;
    }

    public void setDesigneremail(String designeremail) {
        this.designeremail = designeremail;
    }

    public String getDesignedgames() {
        return designedgames;
    }

    public void setDesignedgames(String designedgames) {
        this.designedgames = designedgames;
    }

}
