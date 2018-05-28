package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.ride
*/

import java.io.*;

/**
 * 7/15/15
 */
class ride {

    static String ufo;
    static String followers;
    //private static final String directory = "/Users/cravuri/Documents/";
    //private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "practice.ride.in" + fileSuffix));
        ufo = text.readLine();
        followers = text.readLine();
    }

    public static int multiply(String str) {
        int x = 1;
        for (int i = 0; i < str.length(); i++) {
            int temp = str.charAt(i) - 'A' + 1;
            x = x * temp;
        }
        return x;
    }

    public static boolean go() {
        int u = multiply(ufo) % 47;
        int f = multiply(followers) % 47;
        return u == f;
    }

    public static void writeFile(boolean bool) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "practice.ride.out" + fileSuffix));
        if (bool) {
            text.write("GO");
        } else {
            text.write("STAY");
        }
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(go());
    }
}
