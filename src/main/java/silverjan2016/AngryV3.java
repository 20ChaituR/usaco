package silverjan2016;/*
ID: cravuri
LANG: JAVA
TASK: silverjan2016.AngryV3
*/

import java.util.*;

public class AngryV3 {

    static int[] balePos;
    static int maxBombs;

    public static int calcMin(Set<Integer> visited, int numOfBombs) {
        if (numOfBombs == maxBombs) {
            List<Integer> visited2 = new ArrayList<>();
            equals(visited2, visited);
            Collections.sort(visited2);
            int max = -1;
            for (int i = 0; i < visited2.size() - 1; i++) {
                int x = visited2.get(i + 1) - visited2.get(i);
                if (x > max) {
                    max = x;
                }
            }
            return max;
        }
        Set<Integer> visited2 = new HashSet<>();
        equals(visited2, visited);
        int max = -1;
        for (int i = 0; i < balePos.length; i++) {
            int bale = balePos[i];
            visited2.add(i);
            int x = calcMin(visited2, numOfBombs + 1);
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    public static void equals(Set<Integer> a, Set<Integer> b) {
        for (int x : b) {
            a.add(x);
        }
    }

    public static void equals(List<Integer> a, Set<Integer> b) {
        for (int x : b) {
            a.add(x);
        }
    }

    public static void main(String[] args) {
        Arrays.sort(balePos);
        int minBlast = 0;
        if (maxBombs >= balePos.length) {
            minBlast = 0;
        }
        if (maxBombs == 1) {
            minBlast = balePos[balePos.length - 1] - balePos[0];
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(balePos[0]);
        visited.add(balePos[balePos.length - 1]);
        calcMin(visited, 1);
    }

}
