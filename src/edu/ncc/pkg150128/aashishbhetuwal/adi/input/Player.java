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
public class Player {

    private String playername, playeremail, password, conpassword, playerlocation;
    private int playerid;

    public Player(int playerid) {
        this.playerid = playerid;
    }

    public Player() {
    }

    public Player(String playername, String playeremail, String password, String conpassword, String playerlocation) {
        this.playername = playername;
        this.playeremail = playeremail;
        this.password = password;
        this.conpassword = conpassword;
        this.playerlocation = playerlocation;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getPlayeremail() {
        return playeremail;
    }

    public void setPlayeremail(String playeremail) {
        this.playeremail = playeremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }

    public String getPlayerlocation() {
        return playerlocation;
    }

    public void setPlayerlocation(String playerlocation) {
        this.playerlocation = playerlocation;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

}
