/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import Graph.Graph;
import PathFinders.HamiltonianCycle;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class WarriorsCamp {

    private static Warriors Emperor, ChiefManagement, ChiefMilitary;
    private static ArrayList<Warriors> Generals;

    public static void organizeCamp(ArrayList<Warriors> warriors) {
        Generals = new ArrayList<>();
        for (Warriors warrior : warriors) {
            if (warrior.getRole().equals("Emperor")) {
                WarriorsCamp.setEmperor(warrior);
            } else if (warrior.getRole().equals("Chief Of Military")) {
                WarriorsCamp.setChiefMilitary(warrior);
            } else if (warrior.getRole().equals("Chief Of Management")) {
                WarriorsCamp.setChiefManagement(warrior);
            } else if(warrior.getRole().equals("null")){
                WarriorsCamp.addGeneral(warrior);
            }else{
                System.out.println("Invalid type of role set for the generals. Please check on the Warriors.json file");
                System.exit(0);
            }
        }
        if(Emperor==null||ChiefManagement==null||ChiefMilitary==null){
            System.out.println("Invalid Warriors Data input, Please check on Warriors.json file. Might have missing emperor or chiefs");
            System.exit(0);
        }
    }

    public static Warriors getEmperor() {
        return Emperor;
    }

    public static void setEmperor(Warriors Emperor) {
        WarriorsCamp.Emperor = Emperor;
    }

    public static Warriors getChiefManagement() {
        return ChiefManagement;
    }

    public static void setChiefManagement(Warriors ChiefManagement) {
        WarriorsCamp.ChiefManagement = ChiefManagement;
    }

    public static Warriors getChiefMilitary() {
        return ChiefMilitary;
    }

    public static void setChiefMilitary(Warriors ChiefMilitary) {
        WarriorsCamp.ChiefMilitary = ChiefMilitary;
    }

    public static ArrayList<Warriors> getGenerals() {
        return Generals;
    }

    private static void addGeneral(Warriors generals) {
        if (generals.getIntelligence() > generals.getStrength()) {
            generals.setRole("Department of Management");
            WarriorsCamp.Generals.add(generals);
        } else if (generals.getIntelligence() < generals.getStrength()) {
            generals.setRole("Department of Military");
            WarriorsCamp.Generals.add(generals);

        }
    }

    public static Warriors getGeneral(String name) {
        for (Warriors general : WarriorsCamp.Generals) {
            if (general.getName().equals(name)) {
                return general;
            }
        }
        System.out.println("General not found");
        return null;
    }
    public static boolean hasGeneral(String name){
        for (Warriors general : WarriorsCamp.Generals) {
            if (general.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void showHierachy() {
        System.out.println("Hierachy of Generals\n*----------------------------------------------------------------*");
        WarriorsTree.setEmperor(Emperor);
        WarriorsTree.setChiefOfManagement(ChiefManagement);
        WarriorsTree.setChiefOfMilitary(ChiefMilitary);
        WarriorsTree.addGenerals(Generals);
        WarriorsTree.printTree();
    }

    private static class WarriorsTree {

        private static Node emperor;
        private static Node chiefOfMilitary;
        private static Node chiefOfManagement;

        private static void setEmperor(Warriors emperor) {
            WarriorsTree.emperor = new Node(emperor);
        }

        private static void setChiefOfMilitary(Warriors chiefOfMilitary) {
            WarriorsTree.chiefOfMilitary = new Node(chiefOfMilitary);
            if (!WarriorsTree.emperor.children.contains(WarriorsTree.chiefOfMilitary)) {
                WarriorsTree.emperor.children.add(WarriorsTree.chiefOfMilitary);
            }
        }

        private static void setChiefOfManagement(Warriors chiefOfManagement) {
            WarriorsTree.chiefOfManagement = new Node(chiefOfManagement);
            if (!WarriorsTree.emperor.children.contains(WarriorsTree.chiefOfManagement)) {
                WarriorsTree.emperor.children.add(WarriorsTree.chiefOfManagement);
            }
        }

        private static void addGenerals(ArrayList<Warriors> generals) {
            for (Warriors general : generals) {
                Node warrior = new Node(general);
                if (warrior.info.getRole().equals("Department of Management")) {
                    chiefOfManagement.children.add(warrior);
                } else if (warrior.info.getRole().equals("Department of Military")) {
                    chiefOfMilitary.children.add(warrior);
                }
            }
        }

        private static void printTree() {
            printNode(emperor, 0);
        }

        private static void printNode(Node node, int level) {
            if (node == null) {
                return;
            }

            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println("- " + node.info.getName() + " (" + node.info.getRole() + ")");

            for (Node child : node.children) {
                printNode(child, level + 1);
            }
        }

        private static class Node {

            private Warriors info;
            private ArrayList<Node> children;

            public Node(Warriors info) {
                this.info = info;
                this.children = new ArrayList<>();
            }

        }   

    }
}
