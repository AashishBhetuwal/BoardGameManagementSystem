/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncc.pkg150128.aashishbhetuwal.adi.input;

import javax.swing.JComboBox;

public class Category {

    private int categoryid;
    private String gamecategory;

    public Category() {
    }

    public Category(int categoryid, String gamecategory) {
        this.categoryid = categoryid;
        this.gamecategory = gamecategory;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getGamecategory() {
        return gamecategory;
    }

    public void setGamecategory(String gamecategory) {
        this.gamecategory = gamecategory;
    }

}
