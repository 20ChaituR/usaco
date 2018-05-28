/*
ID: cravuri
LANG: JAVA
TASK: BadMilk
*/

import java.io.*;
import java.util.*;

public class BadMilk {

    static int numOfMilks;
    static int numOfPeople;
    static HashMap<Integer, List<int[]>> milk = new HashMap<>();
    static int[][] sickness;
//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";


    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "badmilk.in" + fileSuffix));
        StringTokenizer st = new StringTokenizer(text.readLine());
        numOfPeople = Integer.parseInt(st.nextToken());
        numOfMilks = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        sickness = new int[s][2];
        List<int[]> newList = new ArrayList<>();
        for (int i = 1; i <= numOfPeople; i++) {
            milk.put(i, newList);
        }
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(text.readLine());
            int p = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            List<int[]> a = equals(milk.get(p));
            a.add(new int[] {m, t});
            milk.put(p, a);
        }
        for (int i = 0; i < sickness.length; i++) {
            st = new StringTokenizer(text.readLine());
            int p = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            sickness[i] = new int[] {p, t};
        }
    }

//    public static int minMedicine() {
//        HashMap<Integer, Integer> badMilks = new HashMap<>();
//        for (int i = 1; i <= numOfMilks; i++) {
//            badMilks.put(i, 0);
//        }
//        for (int i = 0; i < sickness.length; i++) {
//            int person = sickness[i][0];
//            List<int[]> allDrinks = equals(milk.get(person));
//            for (int j = 0; j < allDrinks.size(); j++) {
//                if (allDrinks.get(j)[1] <= sickness[i][1]) {
//                    int x = badMilks.get(allDrinks.get(j)[0]);
//                    badMilks.put(allDrinks.get(j)[0], x + 1);
//                }
//            }
//        }
//        int max = 0;
//        for (int i = 1; i <= numOfMilks; i++) {
//            int num = 0;
//            if (badMilks.get(i) == sickness.length) {
//                for (int j = 1; j <= numOfPeople; j++) {
//                    if (milk.get(j) != null) {
//                        List<int[]> allMilks = equals(milk.get(j));
//                        boolean contain = false;
//                        for (int k = 0; k < allMilks.size(); k++) {
//                            if (allMilks.get(k)[0] == i) {
//                                contain = true;
//                            }
//                        }
//                        if (contain) {
//                            num++;
//                        }
//                    }
//                }
//            }
//            if (num > max) {
//                max = num;
//            }
//        }
//        return max;
//    }

    public static int minMedicine() {
        int max = 0;
        for (int i = 1; i <= numOfMilks; i++) {             // Loop through all milks
            boolean sickMilk = true;                        // Is the milk possibly bad?
            for (int j = 0; j < sickness.length; j++) {
                int p = sickness[j][0];
                List<int[]> drinks = equals(milk.get(p));
                boolean drank = false;
                for (int k = 0; k < drinks.size(); k++) {
                    int[] drink = drinks.get(k);
                    if (drink[0] == i && drink[1] < sickness[j][1]) {
                        drank = true;
                    }
                }
                if (!drank) {
                    sickMilk = false;
                }
            }
            if (sickMilk) {                                 // If the milk is bad
                int num = 0;
                for (int j = 1; j <= numOfPeople; j++) {    // See who drank it
                    List<int[]> newList = new ArrayList<>();
                    List<int[]> allDrinks = equals(milk.get(j));
                    if (allDrinks != newList) {
                        boolean drank = false;
                        for (int k = 0; k < allDrinks.size(); k++) {
                            if (allDrinks.get(k)[0] == i) {
                                drank = true;
                            }
                        }
                        if (drank) {
                            num++;
                        }
                    }
                }
                if (num > max) {
                    max = num;
                }
            }
        }
        return max;
    }

    public static List<int[]> equals(List<int[]> a) {
        List<int[]> newList = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            newList.add(a.get(i));
        }
        return newList;
    }

    public static void writeFile(int max) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "badmilk.out" + fileSuffix));
        text.write(max + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minMedicine());
    }
}
