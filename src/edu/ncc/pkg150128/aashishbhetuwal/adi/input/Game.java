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
public class Game {

    private int gameid, minplayer, optplayer, maxplayer, availabletime;
    private String gamename;
    int designerId, catId;

    public Game() {
    }

    public Game(int gameid, int minplayer, int optplayer, int maxplayer, String game_name, String designer_name, String game_category) {
        this.gameid = gameid;
        this.minplayer = minplayer;
        this.optplayer = optplayer;
        this.maxplayer = maxplayer;
        this.gamename = gamename;
        this.designerId = designerId;
        this.catId = catId;
    }

    public Game(int availabletime) {
        this.availabletime = availabletime;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public int getDesignerId() {
        return designerId;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public int getGamecategoryId() {
        return catId;
    }

    public void setGamecategoryId(int catId) {
        this.catId = catId;
    }

    public int getMinplayer() {
        return minplayer;
    }

    public void setMinplayer(int minplayer) {
        this.minplayer = minplayer;
    }

    public int getOptplayer() {
        return optplayer;
    }

    public void setOptplayer(int optplayer) {
        this.optplayer = optplayer;
    }

    public int getMaxplayer() {
        return maxplayer;
    }

    public void setMaxplayer(int maxplayer) {
        this.maxplayer = maxplayer;
    }

    public int getAvailabletime() {
        return availabletime;
    }

    public void setAvailabletime(int availabletime) {
        this.availabletime = availabletime;
    }

    @Override
    public String toString() {
        return "Game{" + "gameid=" + gameid + ", gamename=" + gamename + ", designerId=" + designerId + ", catId=" + catId + '}';
    }

}
