/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class StrawBoat {

    private static String[] boatDirection;
    private static int[] arrowReceived;
  

    public static void ArrowBorrowing() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> strawmen = new HashMap<>();
        HashMap<String, Integer> usedAttempts = new HashMap<>();
        String[] directions = {"Front", "Left", "Right", "Back"};   
        System.out.println("The river field is now filled with fog, lets start capturing some arrows from Cao Cao");
        System.out.println("Enter the number of straw men for each direction between range 0-100");
        for (String direction : directions) {
            while (true) {
                try {
                    System.out.print(direction + ": ");
                    int value = sc.nextInt();
                    if(value>100||value<0){
                        throw new IllegalArgumentException();
                    }
                    strawmen.put(direction, value);
                    usedAttempts.put(direction, 0);
                    break; // Exit the loop on valid input
                } catch (IllegalArgumentException|InputMismatchException e) {
                    System.out.println("Invalid Input!! Please enter again\n");
                    sc.nextLine();
                }
            }
        }
        sc.nextLine();
        System.out.println("");
        outerloop:while (true) {
            try {
                System.out.println("Enter the number of arrows for each wave in descending order, space to indicate a seperate wave:");
                String waves = sc.nextLine();
                String[] temp = waves.split("\\s+");
                int[] arrows = new int[temp.length];
                int prevArrow = Integer.MAX_VALUE;
                for (int i = 0; i < temp.length; i++) {
                    int arrow = Integer.parseInt(temp[i]);
                    if(arrow<0){
                        throw new IllegalArgumentException();
                    }
                    if (arrow > prevArrow) {
                        System.out.println("Error!!!! Arrows should be in descending order.\n");
                        continue outerloop;
                    }
                    prevArrow = arrow;
                }
                for (int i = 0; i < arrows.length; i++) {
                    arrows[i] = Integer.parseInt(temp[i]);
                }
                arrowReceived = new int[arrows.length];
                boatDirection = new String[arrows.length];

                int totalarrow = ArrowMaximizer(strawmen, arrows, usedAttempts);
                System.out.println();
                System.out.println("Capturing Arrows.....");
                Thread.sleep(1000);
                System.out.println();
                String output = "Arrow Capturing Outcome:\n";
                output += ("Boat direction: " + Arrays.toString(boatDirection) + "\n");
                output += ("Arrow received: " + Arrays.toString(arrowReceived) + "\n");
                output += "Total arrow received: " + totalarrow+" arrows";
                System.out.println(output);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again\n");
            }
        }

    }

    private static int ArrowMaximizer(HashMap<String, Integer> strawmenDirection, int[] arrows, HashMap<String, Integer> usedAttempts) {
        int totalarrow = 0;
        PriorityQueue<String> directionMostStrawmen = new PriorityQueue<>((d1, d2) -> strawmenDirection.get(d2).compareTo(strawmenDirection.get(d1)));
        directionMostStrawmen.addAll(Arrays.asList("Front", "Back", "Left", "Right"));
        String lastDirection = "";
        for (int i = 0; i < arrows.length; i++) {
            String direction = getStrawmenDirection(directionMostStrawmen, lastDirection);
            int strawmen = strawmenDirection.get(direction);
            int arrow = (int) Math.floor(arrows[i] * (strawmen / 100.0));
            if (arrow == 0||arrows[i]==0) {
                Arrays.fill(boatDirection, i, arrows.length, "No Arrows To Receive");
                Arrays.fill(arrowReceived, i, arrows.length, 0);
                break;
            }

            
            arrowReceived[i] = arrow;
            boatDirection[i] = direction;
            totalarrow += arrow;
            strawmenDirection.put(direction, StrawMenDepletion(usedAttempts.get(direction), strawmen));
            usedAttempts.put(direction, usedAttempts.get(direction) + 1);
            directionMostStrawmen.add(direction);
            lastDirection = direction;
        }
        return totalarrow;
    }

    private static String getStrawmenDirection(PriorityQueue<String> directionMostStrawmen, String lastDirection) {
        String curDirection = directionMostStrawmen.poll();
        if (curDirection.equals(lastDirection)) {
            String temp = curDirection;
            curDirection = directionMostStrawmen.poll();
            directionMostStrawmen.add(temp);
        }
        return curDirection;
    }

    private static int StrawMenDepletion( int usedAttempts, int currentStrawmen) {
        switch (usedAttempts) {
            case 0:
                return (int) Math.floor(currentStrawmen * 0.8);
            case 1:
                return (int) Math.floor(Math.ceil(currentStrawmen / 0.8) * 0.4);
            case 2:
                return (int) Math.floor(Math.ceil(currentStrawmen / 0.6) * 0);
            default:
                return 0;
        }
    }
}
