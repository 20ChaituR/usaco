package silverfeb2016;/*
ID: cravuri
LANG: JAVA
TASK: silverfeb2016.CBarnV2
*/

import java.io.*;
import java.util.*;

public class CBarnV2 {

    static int[] cowPos;
    static Set<Integer> emptyPlaces = new HashSet<>();
    static int n = 0;
    static int originalI;
    static Set<Integer> allCosts = new HashSet<>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("cbarn.in"));
        n = Integer.parseInt(text.readLine());
        cowPos = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(text.readLine());
            if (a == 0) {
                emptyPlaces.add(i + 1);
            } else {
                for (int j = 0; j < a; j++) {
                    cowPos[k] = i + 1;
                    k++;
                }
            }
        }
    }

    public static int minEnergy() {
        for (int i = 0; i < n; i++) {
            setAllCosts(i, 0, emptyPlaces, cowPos);
        }
        int x = Integer.MAX_VALUE;
        for (int element : allCosts) {
            if (element < x) {
                x = element;
            }
        }
        return x;
    }

    public static void setAllCosts(int i, int currCost, Set<Integer> oldEmpty, int[] cowPos) {
        int a = cowPos[i];
        if (i == originalI - 1 || (originalI == 0 && i >= n - 1)) {
            for (int newPos : oldEmpty) {
                int cost;
                if (newPos > a) {
                    cost = (int) Math.pow(newPos - a + 1, 2);
                } else {
                    cost = (int) Math.pow(n - a + newPos + 1, 2);
                }
                allCosts.add(currCost + cost);
            }
            return;
        }
        cowPos[i] = -1;
        if (!contains(a, cowPos)) {
            cowPos[i] = a;
            int next = i + 1;
            if (i == n - 1) {
                next = 0;
            }
            setAllCosts(next, currCost + 1, oldEmpty, cowPos);
        }
        cowPos[i] = a;
        for (int newPos : oldEmpty) {
            int cost;
            if (newPos > a) {
                cost = (int) Math.pow(newPos - a + 1, 2);
            } else {
                cost = (int) Math.pow(n - a + newPos + 1, 2);
            }
            int next = i + 1;
            if (i == n - 1) {
                next = 0;
            }
            Set<Integer> newEmpty = new HashSet<>();
            equals(newEmpty, oldEmpty);
            newEmpty.remove(newPos);
            int[] newCowPos = new int[cowPos.length];
            equals(newCowPos, cowPos);
            newCowPos[i] = newPos;
            if (!contains(a, newCowPos)) {
                newEmpty.add(a);
            }
            setAllCosts(next, currCost + cost, newEmpty, newCowPos);
        }
    }

    public static boolean contains(int x, int[] cowPos) {
        for (int element : cowPos) {
            if (x == element) {
                return true;
            }
        }
        return false;
    }

    public static void equals(Set<Integer> a, Set<Integer> b) {
        for (int element : b) {
            a.add(element);
        }
    }

    public static void equals(int[] a, int[] b) {
        for (int i = 0; i < b.length; i++) {
            a[i] = b[i];
        }
    }

    public static void writeFile(int a) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("cbarn.out"));
        text.write(a + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minEnergy());
    }

}
