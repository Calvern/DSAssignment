/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class StrawBoat {

    public static String[] boatDirection;
    public static int[] arrowReceived;

    public static void ArrowBorrowing() {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> strawmen = new HashMap<>();
        HashMap<String, Integer> usedAttempts = new HashMap<>();
        System.out.println("Enter the number of straw men");
        System.out.print("Front: ");
        int front = sc.nextInt();
        strawmen.put("Front", front);
        usedAttempts.put("Front", 0);
        System.out.print("Left: ");
        int left = sc.nextInt();
        strawmen.put("Left", left);
        usedAttempts.put("Left", 0);
        System.out.print("Right: ");
        int right = sc.nextInt();
        strawmen.put("Right", right);
        usedAttempts.put("Right", 0);
        System.out.print("Back: ");
        int back = sc.nextInt();
        strawmen.put("Back", back);
        usedAttempts.put("Back", 0);
        sc.nextLine();

        System.out.println("Enter the number of arrows for each wave in descending order, space to indicate a seperate wave:");
        String waves = sc.nextLine();
        String[] temp = waves.split("\\s+");
        int[] arrows = new int[temp.length];

        int prevArrow = Integer.MAX_VALUE;
        for (int i = 0; i < temp.length; i++) {
            int arrow = Integer.parseInt(temp[i]);
            if (arrow >= prevArrow) {
                System.out.println("Error!!!! Arrows should be in descending order.");
                return;
            }
            prevArrow = arrow;
        }

        for (int i = 0; i < arrows.length; i++) {
            arrows[i] = Integer.parseInt(temp[i]);
        }
        arrowReceived = new int[arrows.length];
        boatDirection = new String[arrows.length];
        int totalarrow = ArrowMaximizer(strawmen, arrows, usedAttempts);
        System.out.println("Boat direction: " + Arrays.toString(boatDirection));
        System.out.println("Arrow received: " + Arrays.toString(arrowReceived));
        System.out.println(totalarrow);
    }

    private static int ArrowMaximizer(HashMap<String, Integer> strawmenDirection, int[] arrows, HashMap<String, Integer> usedAttempts) {
        int totalarrow = 0;
        PriorityQueue<String> directionMostStrawmen = new PriorityQueue<>((d1, d2) -> strawmenDirection.get(d2).compareTo(strawmenDirection.get(d1)));
        directionMostStrawmen.addAll(Arrays.asList("Front", "Back", "Left", "Right"));
        String lastDirection = "";
        for (int i = 0; i < arrows.length; i++) {
            String direction = getStrawmenDirection(directionMostStrawmen, lastDirection);
            int strawmen = strawmenDirection.get(direction);
            if (strawmen == 0) {
                Arrays.fill(boatDirection, i, arrows.length, "Invalid");
                Arrays.fill(arrowReceived, i, arrows.length, 0);
                break;
            }
            
            int arrow = (int) Math.floor(arrows[i] * (strawmen / 100.0));
            arrowReceived[i] = arrow;
            boatDirection[i] = direction;
            totalarrow += arrow;
            strawmenDirection.put(direction, StrawMenDepletion(direction, usedAttempts.get(direction), strawmen));
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

    private static int StrawMenDepletion(String direction, int usedAttempts, int currentStrawmen) {
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
