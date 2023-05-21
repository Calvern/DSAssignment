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

    private static void formTeam(Teams type, Abilities ab) {
        team = new ArrayList<>();
        Warriors[] generals = WarriorsSorter.getSortedGenerals(ab);
        for (int i = 0; i < generals.length - 2; i++) {
            int start = i + 1;
            int end = generals.length - 1;
            while (start < end) {
                int sum = generals[i].getAbility(ab) + generals[start].getAbility(ab) + generals[end].getAbility(ab);
                if (sum >= type.getMinscore() && sum < type.getMaxscore()) {
                    ArrayList<Warriors> warriors = new ArrayList<>(Arrays.asList(generals[i], generals[start], generals[end]));
                    team.add(warriors);
                    start++;
                } else if (sum < type.getMinscore()) {
                    start++;
                } else {
                    end--;
                }
            }
        }
    }
}
