package practice;/*
ID: cravuri
LANG: JAVA
TASK: baseball
*/

import java.io.*;
import java.util.Arrays;

class Baseball {

    static int[] cowPos;
//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";


    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "baseball.in" + fileSuffix));
        cowPos = new int[Integer.parseInt(text.readLine())];
        for (int i = 0; i < cowPos.length; i++) {
            cowPos[i] = Integer.parseInt(text.readLine());
        }
        Arrays.sort(cowPos);
    }

    public static int throwing() {
        int numOfCombos = 0;
        for (int i = 0; i < cowPos.length - 2; i++) {
            for (int j = i + 1; j < cowPos.length - 1; j++) {
                int x = cowPos[j] - cowPos[i];
                for (int k = j + 1; k < cowPos.length; k++) {
                    int y = cowPos[k] - cowPos[j];
                    if (y >= x && y <= 2*x) {
                        numOfCombos++;
                    }
                }
            }
        }
        return numOfCombos;
    }

    public static void writeFile(int numOfCombos) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "baseball.out" + fileSuffix));
        text.write(numOfCombos + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(throwing());
        System.exit(0);
    }

}
