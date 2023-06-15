/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threekingdoms;

import threekingdoms.Abilities;


/*
 * @author user
 */
public class Warriors {

    private String name;
    private ArmyTypes army_type;
    private String role;
    private int strength, leadership, intelligence, politic, hit_point;

    public Warriors(String name, ArmyTypes army_type, String role, int strength, int leadership, int intelligence, int politic, int hit_point){

        this.name = name;
        this.army_type = army_type;
        this.role = role;

        if (strength < 0 || strength > 100 || leadership < 0 || leadership > 100 || intelligence < 0 || intelligence > 100 || politic < 0 || politic > 100 || hit_point < 0 || hit_point > 100) {
            throw new IllegalArgumentException();
        }
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hit_point = hit_point;

    }

    /*public Warriors(String name, ArmyTypes army_type, int strength, int leadership, int intelligence, int politic, int hit_point) {
        this.name = name;
        this.army_type = army_type;
        this.role=null;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hit_point = hit_point;
    }*/
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArmyTypes getArmy_type() {
        return army_type;
    }

    public void setArmy_type(ArmyTypes army_type) {
        this.army_type = army_type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPolitic() {
        return politic;
    }

    public void setPolitic(int politic) {
        this.politic = politic;
    }

    public int getHit_point() {
        return hit_point;
    }

    public void setHit_point(int hit_point) {
        this.hit_point = hit_point;
    }

    public int getAbility(Abilities ab) {
        switch (ab) {
            case STRENGTH:
                return this.getStrength();
            case LEADERSHIP:
                return this.getLeadership();
            case INTELLIGENCE:
                return this.getIntelligence();
            case POLITIC:
                return this.getPolitic();
            case HIT_POINT:
                return this.getHit_point();
            default:
                return -1;

        }

    }

    public String toString() {
        return this.getName();
    }

}
