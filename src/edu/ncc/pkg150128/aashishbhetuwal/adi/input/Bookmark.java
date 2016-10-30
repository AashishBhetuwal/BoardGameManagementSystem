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
public class Bookmark {

    private int bookmarkid, minplayer, optplayer, maxplayer, availabletime;
    private String gamename, playername;

    public Bookmark(int bookmarkid, int minplayer, int optplayer, int maxplayer, int availabletime, String gamename) {
        this.bookmarkid = bookmarkid;
        this.minplayer = minplayer;
        this.optplayer = optplayer;
        this.maxplayer = maxplayer;
        this.availabletime = availabletime;
        this.gamename = gamename;
    }

    public Bookmark(String playername) {
        this.playername = playername;
    }

    public Bookmark() {

    }

    public int getBookmarkid() {
        return bookmarkid;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public void setBookmarkid(int bookmarkid) {
        this.bookmarkid = bookmarkid;
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

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

}
