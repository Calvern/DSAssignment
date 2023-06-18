/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author user
 */
public class TeamFormer {

    private static ArrayList<ArrayList<Warriors>> team;

    public static ArrayList<ArrayList<Warriors>> getTeam(Teams type, Abilities ab) {
        TeamFormer.formTeam(type, ab);
        return team;
    }

    public static Teams evaluateTeam(String[] warriors, Abilities ab) {
        int totalValue = 0;
        for (String warrior : warriors) {
            Warriors war = WarriorsCamp.getGeneral(warrior);
            totalValue += war.getAbility(ab);
        }
        if (totalValue <= Teams.C_TEAM.getMaxscore()) {
            return Teams.C_TEAM;
        } else if (totalValue <= Teams.B_TEAM.getMaxscore()) {
            return Teams.B_TEAM;
        } else if (totalValue <= Teams.A_TEAM.getMaxscore()) {
            return Teams.A_TEAM;
        } else {
            return Teams.S_TEAM;
        }
    }

    private static void formTeam(Teams type, Abilities ab) {
        team = new ArrayList<>();
        Warriors[] generals = WarriorsSorter.getSortedGenerals(ab);
        for (int i = 0; i < generals.length - 2; i++) {
            int start = i + 1;
            int end = generals.length - 1;
            while (start < end) {
                int sum = generals[i].getAbility(ab) + generals[start].getAbility(ab) + generals[end].getAbility(ab);
                if (sum >= type.getMinscore() && sum <= type.getMaxscore()) {
                    ArrayList<Warriors> warriors = new ArrayList<>(Arrays.asList(generals[i], generals[start], generals[end]));
                    team.add(warriors);
                    end--;

                } else if (sum < type.getMinscore()) {
                    end--;

                } else {
                    start++;
                }
            }
        }
    }
}
