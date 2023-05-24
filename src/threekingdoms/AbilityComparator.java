/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.Comparator;

/**
 *
 * @author user
 */
public class AbilityComparator {
    public static int ComparatorSwitcher(Abilities ab,Warriors w1,Warriors w2){
        switch(ab){
            case STRENGTH:
                return StrengthComparator(w1,w2);
            case LEADERSHIP:
                return LeadershipComparator(w1,w2);
            case INTELLIGENCE:
                return IntelligenceComparator(w1,w2);
            case POLITIC:
                return PoliticComparator(w1,w2);
            case HIT_POINT:
                return HitPointComparator(w1,w2);
            default:
                throw new RuntimeException();// To be comfirmed for this code
        }
    }
    public static int StrengthComparator(Warriors w1, Warriors w2) {
        if (w1.getStrength() > w2.getStrength()) {
            return -1;
        } else if (w2.getStrength() > w1.getStrength()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int LeadershipComparator(Warriors w1, Warriors w2) {
        if (w1.getLeadership() > w2.getLeadership()) {
            return -1;
        } else if (w2.getLeadership() > w1.getLeadership()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int IntelligenceComparator(Warriors w1, Warriors w2) {
        if (w1.getIntelligence() > w2.getIntelligence()) {
            return -1;
        } else if (w2.getIntelligence() > w1.getIntelligence()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int PoliticComparator(Warriors w1, Warriors w2) {
        if (w1.getPolitic() > w2.getPolitic()) {
            return -1;
        } else if (w2.getPolitic() > w1.getPolitic()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int HitPointComparator(Warriors w1, Warriors w2) {
        if (w1.getHit_point() > w2.getHit_point()) {
            return -1;
        } else if (w2.getHit_point() > w1.getHit_point()) {
            return 1;
        } else {
            return 0;
        }
    }
}
