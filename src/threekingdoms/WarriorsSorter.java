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
public class WarriorsSorter {

    public static Warriors[] getSortedGenerals(Abilities ab) {
        ArrayList<Warriors> generals = WarriorsCamp.getGenerals();
        Warriors[]sortedGenerals=new Warriors[generals.size()];
        int i=0;
        for(Warriors general:generals){
            sortedGenerals[i++]=general;
        }
        quickSort(ab,sortedGenerals,0,sortedGenerals.length-1);
        return sortedGenerals;
    }

    private static void quickSort(Abilities ab,Warriors[] array, int start, int end) {
        if (end <= start) {
            return;//base case
        }
        int pivot = partition(ab,array, start, end);
        quickSort(ab,array, start, pivot - 1);
        quickSort(ab,array, pivot + 1, end);
    }

    private static int partition(Abilities ab,Warriors[] array, int start, int end) {
        Warriors pivot = array[end];
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            Warriors cur=array[j];
            if(AbilityComparator.ComparatorSwitcher(ab, cur, pivot)<0){           
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
    }
    public static Warriors[] search(Abilities ab, int target) {
        // get ascending order by attribute type
        Warriors[] sorted = WarriorsSorter.getSortedGenerals(ab);
        int start=getStartingIndex(ab,target,sorted);
        int end=getEndingIndex(ab,target,sorted);
        Warriors[] matched;
        if(start==-1||end==-1){
            matched=null;
        }else{
            matched=new Warriors[end-start+1];
            matched=Arrays.copyOfRange(sorted, start, end+1);
        }
        return matched;
       
        
}
    private static int getStartingIndex(Abilities ab,int target,Warriors[]sorted){
        int index=-1;
        int low=0;
        int high=sorted.length-1;
        while(low<=high){
            int middle=low+(high-low)/2;
            if(sorted[middle].getAbility(ab)>=target){
                low=middle+1;
               // high=middle-1;
            }else{
                //low=middle+1;
                high=middle-1;
            }
            if(sorted[middle].getAbility(ab)==target){
                index=middle;
            }
        }
        return index;
    }
    
    private static int getEndingIndex(Abilities ab,int target,Warriors[]sorted){
        int index=-1;
        int low=0;
        int high=sorted.length-1;
        while(low<=high){
            int middle=low+(high-low)/2;
            if(sorted[middle].getAbility(ab)<=target){
                high=middle-1;
                //low=middle+1;
            }else{
                //high=middle-1;
                low=middle+1;
            }
            if(sorted[middle].getAbility(ab)==target){
                index=middle;
            }
        }
        return index;
    }
}
