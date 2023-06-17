/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threekingdoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class WarriorsSorter {


    public static void soldierArrangement() {

        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("""
                           1.Sorting generals
                           2.Searching generals
                           3.Search for team
                           """);
            System.out.print("Enter your choice:  ");
            try {
                choice = sc.nextInt();
                if (choice < 1 || choice > 3) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid Input!! Please enter again. \n");
                sc.nextLine();
            }
        }
        System.out.println();
        switch (choice) {
            case 1:
                WarriorsSorter.printSortedGenerals();
                break;
            case 2:
                WarriorsSorter.printSearchedGenerals();
                break;
            case 3:
                WarriorsSorter.printTeam();
                break;
        }
    }

    private static void printTeam() {
        Scanner sc = new Scanner(System.in);
        Abilities ab = null;
        int target = -1;
        while (true) {
            System.out.print("1.Strength\n2.Leadership\n3.Intelligence\n4.Politics\n5.Hit Point\nEnter the attiribute that you want to search : ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 ->
                        ab = Abilities.STRENGTH;
                    case 2 ->
                        ab = Abilities.LEADERSHIP;
                    case 3 ->
                        ab = Abilities.INTELLIGENCE;
                    case 4 ->
                        ab = Abilities.POLITIC;
                    case 5 ->
                        ab = Abilities.HIT_POINT;
                    default -> {
                        throw new IllegalArgumentException();
                    }

                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again. \n");
                sc.nextLine();

            }

        }
        System.out.println();
        Teams team = null;
        while (true) {
            System.out.print("1.S Team (Sum of Ability >= 250)\n2.A Team (Sum of Ability >= 220)\n3.B Team (Sum Of Ability >= 190)\n4.C Team (Sum of Ability <  190)\nPlease enter the type of team to be formed: ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 ->
                        team = Teams.S_TEAM;
                    case 2 ->
                        team = Teams.A_TEAM;
                    case 3 ->
                        team = Teams.B_TEAM;
                    case 4 ->
                        team = Teams.C_TEAM;
                    default -> {
                        throw new IllegalArgumentException();
                    }
                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again");
                sc.nextLine();
            }
        }
        System.out.println();
        ArrayList<ArrayList<Warriors>> list = TeamFormer.getTeam(team, ab);
        System.out.println("Teams available:\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(list.get(i));
            System.out.println("");
        }
    }

    private static void printSearchedGenerals() {
        Scanner sc = new Scanner(System.in);
        Abilities ab = null;
        int target = -1;
        while (true) {
            System.out.print("1.Strength\n2.Leadership\n3.Intelligence\n4.Politics\n5.Hit Point\nEnter the attiribute to search  : ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 ->
                        ab = Abilities.STRENGTH;
                    case 2 ->
                        ab = Abilities.LEADERSHIP;
                    case 3 ->
                        ab = Abilities.INTELLIGENCE;
                    case 4 ->
                        ab = Abilities.POLITIC;
                    case 5 ->
                        ab = Abilities.HIT_POINT;
                    default -> {
                        throw new IllegalArgumentException();
                    }

                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again. \n");
                sc.nextLine();

            }
        }
        System.out.println();
        while (true) {
            System.out.print("Please enter the value of the attribute you want to search for ( 0-100 ): ");
            try {
                target = sc.nextInt();
                if (target < 0 || target > 100) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invaldi Input!! Please enter values within 0-100\n");
                sc.nextLine();
            }
        }
        System.out.println();
        Warriors[] warrior = WarriorsSorter.search(ab, target);
        if (warrior == null) {
            System.out.println("No general found");
        } else {
            System.out.println("The generals found:\n");
            for (int i = 0; i < warrior.length; i++) {
                System.out.println((i + 1) + ". " + warrior[i].getName());
            }
        }
    }

    private static void printSortedGenerals() {
        Scanner sc = new Scanner(System.in);
        Abilities ab = null;

        while (true) {
            System.out.print("1.Strength\n2.Leadership\n3.Intelligence\n4.Politics\n5.Hit Point\nEnter the attiribute that you want your general to be sorted: ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 ->
                        ab = Abilities.STRENGTH;
                    case 2 ->
                        ab = Abilities.LEADERSHIP;
                    case 3 ->
                        ab = Abilities.INTELLIGENCE;
                    case 4 ->
                        ab = Abilities.POLITIC;
                    case 5 ->
                        ab = Abilities.HIT_POINT;
                    default -> {
                        throw new IllegalArgumentException();
                    }

                }
                break;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Invalid Input!! Please enter again. \n");
                sc.nextLine();

            }
        }
        System.out.println();
        Warriors[] warrior = getSortedGenerals(ab);
        System.out.println("Generals Sorted By " + ab.toString().toLowerCase());
        for (int i = 0; i < warrior.length; i++) {
            System.out.println((i + 1) + ". " + warrior[i].getName() + " (" + warrior[i].getAbility(ab) + ")");
        }

    }

    public static Warriors[] getSortedGenerals(Abilities ab) {

        ArrayList<Warriors> generals = WarriorsCamp.getGenerals();
        Warriors[] sortedGenerals = new Warriors[generals.size()];
        int i = 0;
        for (Warriors general : generals) {
            sortedGenerals[i++] = general;
        }

        //quickSort(ab, sortedGenerals, 0, sortedGenerals.length - 1);
        mergeSort(sortedGenerals,ab);
        return sortedGenerals;
    }
      private static void mergeSort(Warriors[] sortedGenerals,Abilities ab) {
        int length = sortedGenerals.length;
        if (length <= 1) {//length<2
            return;
        }
        int middle = length / 2;
        Warriors[] leftArray = new Warriors[middle];
        Warriors[] rightArray = new Warriors[length - middle];
        int i = 0;//left array
        int j = 0;//right array
        for (; i < length; i++) {
            if (i < middle) {
                leftArray[i] = sortedGenerals[i];
            } else {
                rightArray[j++] = sortedGenerals[i];
                
            }
        }

        mergeSort(leftArray,ab);
       
        mergeSort(rightArray,ab);

        
        merge(leftArray, rightArray, sortedGenerals,ab);

        
        
    
    }

    private static void merge(Warriors[] leftArray, Warriors[] rightArray, Warriors[] array,Abilities ab) {
        int leftSize = leftArray.length; 
        int rightSize = rightArray.length;
        int i = 0, l = 0, r = 0;
       
        //Check the conditions for merging
        while (l < leftSize && r < rightSize) {
            if (AbilityComparator.ComparatorSwitcher(ab, leftArray[l], rightArray[r])<0) {
                array[i] = leftArray[l];
                i++;
                l++;
            } else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while (l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;

        }
        while(r<rightSize){
            array[i]=rightArray[r];
            i++;
            r++;
        }
    }
   /* private static void quickSort(Abilities ab, Warriors[] array, int start, int end) {
        if (end <= start) {
            return;//base case
        }
        int pivot = partition(ab, array, start, end);
        quickSort(ab, array, start, pivot - 1);
        quickSort(ab, array, pivot + 1, end);
    }

    private static int partition(Abilities ab, Warriors[] array, int start, int end) {
        Warriors pivot = array[end];
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            Warriors cur = array[j];
            if (AbilityComparator.ComparatorSwitcher(ab, cur, pivot) < 0) {
                i++;
                Warriors temp = array[i];
                array[i] = array[j];
                array[j] = temp;

            }
        }
        i++;
        Warriors temp = array[i];
        array[i] = array[end];
        array[end] = temp;
        return i;
    }*/

    public static Warriors[] search(Abilities ab, int target) {
        // get descending order by attribute type
        Warriors[] sorted = WarriorsSorter.getSortedGenerals(ab);
        int start = getStartingIndex(ab, target, sorted);
        int end = getEndingIndex(ab, target, sorted);
        Warriors[] matched;
        if (start == -1 || end == -1) {
            matched = null;
        } else {
            //matched=new Warriors[end-start+1];
            matched = Arrays.copyOfRange(sorted, start, end + 1);
        }
        return matched;

    }

    private static int getStartingIndex(Abilities ab, int target, Warriors[] sorted) {
        int index = -1;
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sorted[middle].getAbility(ab) <= target) {

                high = middle - 1;
            } else {
                low = middle + 1;

            }
            if (sorted[middle].getAbility(ab) == target) {
                index = middle;
            }
        }
        return index;
    }

    private static int getEndingIndex(Abilities ab, int target, Warriors[] sorted) {
        int index = -1;
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sorted[middle].getAbility(ab) >= target) {

                low = middle + 1;
            } else {
                high = middle - 1;

            }
            if (sorted[middle].getAbility(ab) == target) {
                index = middle;
            }
        }
        return index;
    }
}
