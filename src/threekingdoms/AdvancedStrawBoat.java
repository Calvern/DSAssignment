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
public class AdvancedStrawBoat {

    public static String[] boatDirection;
    public static int[] arrowReceived;

    public static void main(String[] args) {
        ArrowBorrowing();
    }

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

        System.out.println("Enter the number of arrows for each wave in any order desired, space to indicate a seperate wave:");
        String waves = sc.nextLine();
        String[] temp = waves.split("\\s+");
        int[] arrows = new int[temp.length];

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
        PriorityQueue<Integer> sortedArrows = new PriorityQueue<>((a, b) -> arrows[b] - arrows[a]);
        for (int i = 0; i < arrows.length; i++) {
            sortedArrows.add(i);
        }
        directionMostStrawmen.addAll(Arrays.asList("Front", "Back", "Left", "Right"));

        while (!sortedArrows.isEmpty()) {
            int curIndex = sortedArrows.poll();
            System.out.println(curIndex);
            String direction = getStrawmenDirection(directionMostStrawmen, curIndex);
            int strawmen = strawmenDirection.get(direction);
            if (strawmen == 0) {
                boatDirection[curIndex] = "Skipped";
                arrowReceived[curIndex] = 0;
                directionMostStrawmen.add(direction);
                continue;
            }

            int arrow = (int) Math.floor(arrows[curIndex] * (strawmen / 100.0));
            arrowReceived[curIndex] = arrow;
            boatDirection[curIndex] = direction;
            totalarrow += arrow;
            strawmenDirection.put(direction, StrawMenDepletion(usedAttempts.get(direction), strawmen));
            usedAttempts.put(direction, usedAttempts.get(direction) + 1);
            directionMostStrawmen.add(direction);

        }
        return totalarrow;
    }

    /*private static String getStrawmenDirection(PriorityQueue<String> directionMostStrawmen, int curIndex) {
        String curDirection = directionMostStrawmen.poll();
        if (curIndex != 0) {
            if (boatDirection[curIndex - 1] != null) {
                if (curDirection.equals(boatDirection[curIndex - 1])) {
                    String temp = curDirection;
                    curDirection = directionMostStrawmen.poll();
                    directionMostStrawmen.add(temp);
                }
            }
        }
        return curDirection;
    }*/private static String getStrawmenDirection(PriorityQueue<String> directionMostStrawmen, int curIndex) {
    String curDirection = directionMostStrawmen.poll();
    if (curIndex != 0 && curIndex + 1 < boatDirection.length) {
        String prevDirection = boatDirection[curIndex - 1];
        String nextDirection = boatDirection[curIndex + 1];
        if ((prevDirection == null || !prevDirection.equals(curDirection))
                && (nextDirection == null || !nextDirection.equals(curDirection))) {
            // Both previous and next directions are different, proceed as usual
            return curDirection;
        } else {
            String removedDirection = null;
            PriorityQueue<String> tempQueue = new PriorityQueue<>();
            while (curDirection.equals(boatDirection[curIndex - 1]) || curDirection.equals(boatDirection[curIndex + 1])) {
               // removedDirection = curDirection; // Store the direction to be removed
                tempQueue.add(curDirection);
                curDirection = directionMostStrawmen.poll();
            }
            // Add the removed direction back to the original queue
            //directionMostStrawmen.add(removedDirection);
            // Add the remaining directions from tempQueue back to the original queue
            directionMostStrawmen.addAll(tempQueue);
            return curDirection;
        }
    } else {
        // Return the current direction as there are no previous or next directions
        return curDirection;
    }
}

    private static int StrawMenDepletion(int usedAttempts, int currentStrawmen) {
        switch (usedAttempts) {
            case 0:
                return (int) Math.floor(currentStrawmen * 0.5);
            case 1:
                return (int) Math.floor(Math.ceil(currentStrawmen / 0.5) * 0);
            default:
                return 0;
        }
    }
}
