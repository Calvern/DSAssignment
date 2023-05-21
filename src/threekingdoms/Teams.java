/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

/**
 *
 * @author user
 */
public enum Teams {
    S_TEAM(250,Integer.MAX_VALUE),
    A_TEAM(220,249),
    B_TEAM(190,219),
    C_TEAM(0,189);
    final private int minscore,maxscore;

    Teams(int minscore,int maxscore) {
        this.minscore=minscore;
        this.maxscore=maxscore;
    }

    public int getMinscore() {
        return minscore;
    }

    public int getMaxscore() {
        return maxscore;
    }

}
