package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.milk2
*/

import java.io.*;
import java.util.*;

class milk3 {

    static int longMilk = 0;
    static int longIdle = 0;
    static List<int[]> farmers = new ArrayList<int[]>();


    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("practice.milk2.in"));
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
        Collections.sort(farmers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });
        int low = farmers.get(0)[0];
        int high = farmers.get(0)[1];
        int milk = high - low;
        int idle = 0;
        for(int i = 1; i < farmers.size(); i++) {
            int[] farmer = farmers.get(i);
            if (farmer[0] <= high) {
                high = Math.max(farmer[1], high);
            } else {
                milk = Math.max(milk, high - low);
                idle = Math.max(idle, farmer[0] - high);
                low = farmer[0];
                high = farmer[1];
            }
        }
        longMilk = milk;
        longIdle = idle;
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("practice.milk2.out"));
        text.write(longMilk + " " + longIdle);
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        longestMilk();
        writeFile();
    }

}
