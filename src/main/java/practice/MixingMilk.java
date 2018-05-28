/*
ID: cravuri
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class milk {

    static int neededMilk;
    static int[][] farmers;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("milk.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        neededMilk = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        farmers = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(text.readLine());
            int[] a = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            farmers[i] = a;
        }
    }

    public static int minCost() {
        Arrays.sort(farmers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int cost = 0;
        int milk = 0;
        for (int[] farmer : farmers) {
            int x = neededMilk - milk;
            if (farmer[1] <= x) {
                milk = milk + farmer[1];
                cost = cost + farmer[1] * farmer[0];
            } else {
                milk = milk + x;
                cost = cost + x * farmer[0];
            }
            if (milk >= neededMilk) {
                break;
            }
        }
        return cost;
    }

    public static void writeFile(int minCost) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("milk.out"));
        text.write(minCost + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minCost());
    }

}
