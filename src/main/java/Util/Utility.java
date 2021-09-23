package Util;

import javafx.util.Pair;

import java.util.List;

public class Utility {

    public static double distance(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2){
        int x = p2.getKey()-p1.getKey();
        int y = p2.getValue()-p1.getValue();

        return Math.sqrt((x*x)+(y*y));
    }



}


