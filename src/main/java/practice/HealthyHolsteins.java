/*
ID: cravuri
LANG: JAVA
TASK: holstein
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class holstein2 {

    static int[] minVitamins;
    static int[][] feeds;
    static int[] realFeeds;
    static int numOfFeeds = Integer.MAX_VALUE;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("holstein.in"));
        int n = sc.nextInt();
        minVitamins = new int[n];
        for (int i = 0; i < n; i++) {
            minVitamins[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        realFeeds = new int[m];
        feeds = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                feeds[i][j] = sc.nextInt();
            }
        }
    }

    public static void minVitamins(int[] usedFeeds, int count) {
        if (count >= feeds.length) {
            return;
        }
        if (possible(usedFeeds)) {
            if (count + 1 < numOfFeeds) {
                System.arraycopy(usedFeeds, 0, realFeeds, 0, usedFeeds.length);
                numOfFeeds = count;
            }
        }
        for (int i = 0; i < feeds.length; i++) {
            int[] feed = new int[usedFeeds.length];
            System.arraycopy(usedFeeds, 0, feed, 0, usedFeeds.length);
            feed[count] = i;
            minVitamins(feed, count);
        }
    }

    public static boolean possible(int[] usedFeeds) {
        int[] vitaminsUsed = new int[minVitamins.length];
        System.arraycopy(minVitamins, 0, vitaminsUsed, 0, minVitamins.length);
        for (int i = 0; i < usedFeeds.length; i++) {
            if (usedFeeds[i] == -1) {
                break;
            }
            for (int j = 0; j < feeds[usedFeeds[i]].length; j++) {
                vitaminsUsed[j] -= feeds[usedFeeds[i]][j];
            }
        }
        for (int vitaminUsed : vitaminsUsed) {
            if (vitaminUsed > 0) {
                return false;
            }
        }
        return true;
    }

    public static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("holstein.out"));
        pr.print(numOfFeeds);
        for (int realFeed : realFeeds) {
            if (realFeed == -1) {
                break;
            }
            pr.print(" " + (realFeed + 1));
        }
        pr.println();
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        int[] usedFeeds = new int[realFeeds.length];
        for (int i = 0; i < usedFeeds.length; i++) {
            usedFeeds[i] = -1;
        }
        minVitamins(usedFeeds, -1);
        writeFile();
    }

}
