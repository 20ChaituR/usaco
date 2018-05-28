package practice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: cravuri
LANG: JAVA
TASK: practice.milk2
*/

class milk2 {

    static int longMilk = 0;
    static int longIdle = 0;
    static List<int[]> farmers = new ArrayList<int[]>();
    static int leastFarmer = Integer.MAX_VALUE;
    static int greatestFarmer = Integer.MIN_VALUE;
//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";


    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "practice.milk2.in" + fileSuffix));
        int n = Integer.parseInt(text.readLine());
        for (int i = 0; i < n; i++) {
            int[] farmer = new int[2];
            StringTokenizer st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            farmer[0] = a;
            farmer[1] = b;
            farmers.add(farmer);
        }
    }

    public static void longestMilk() {
        int x = 0;
        int y = 0;
        for (int i = leastFarmer; i <= greatestFarmer; i++) {
            boolean idle = true;
            for (int[] farmer : farmers) {
                if (i >= farmer[0] && i < farmer[1]) {
                    idle = false;
                }
            }
            if (idle) {
                if (x > longMilk) {
                    longMilk = x;
                }
                x = -1;
            } else {
                if (y > longIdle) {
                    longIdle = y;
                }
                y = -1;
            }
            x++;
            y++;
        }
        int asdf = 0;
    }

    public static void findFarmers() {
        for (int[] farmer : farmers) {
            if (farmer[0] < leastFarmer) {
                leastFarmer = farmer[0];
            }
            if (farmer[1] > greatestFarmer) {
                greatestFarmer = farmer[1];
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "practice.milk2.out" + fileSuffix));
        text.write(longMilk + " " + longIdle);
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        findFarmers();
        longestMilk();
        writeFile();
    }
}
