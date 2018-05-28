package practice;/*
ID: cravuri
LANG: JAVA
TASK: cowfind
*/

import java.io.*;

public class Cowfind {

    static String[] parenthesis;
//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "cowfind.in" + fileSuffix));
        parenthesis = text.readLine().split("");
    }

    public static int totalCows() {
        int i = 0;
        int counter = 0;
        int total = 0;
        boolean right = false;
        boolean left = false;
        while (i < parenthesis.length) {
            if (parenthesis[i].equals("(") && right) {
                counter++;
            }
            if (parenthesis[i].equals(")") && left) {
                total = total + counter;
            }
            if (parenthesis[i].equals("(")) {
                right = true;
                left = false;
            } else {
                right = false;
                left = true;
            }
            i++;
        }
        return total;
    }

    public static void writeFile(int total) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "cowfind.out" + fileSuffix));
        text.write(total + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(totalCows());
        System.exit(0);
    }
}
