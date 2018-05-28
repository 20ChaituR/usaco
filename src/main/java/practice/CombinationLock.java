/*
ID: cravuri
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.StringTokenizer;

class combo {

    static int sizeOfLock;
    static int[] farmerCombo = new int[3];
    static int[] masterCombo = new int[3];

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("combo.in"));
        sizeOfLock = Integer.parseInt(text.readLine());
        StringTokenizer st = new StringTokenizer(text.readLine());
        for (int i = 0; i < 3; i++) {
            farmerCombo[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(text.readLine());
        for (int i = 0; i < 3; i++) {
            masterCombo[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static int numOfCombos() {
        int numOfCombos = 0;
        for (int i = 1; i <= sizeOfLock; i++) {
            for (int j = 1; j <= sizeOfLock; j++) {
                for (int k = 1; k <= sizeOfLock; k++) {
                    if (correctCombo(i, j, k, farmerCombo)) {
                        numOfCombos++;
                    } else if (correctCombo(i, j, k, masterCombo)) {
                        numOfCombos++;
                    }
                }
            }
        }
        return numOfCombos;
    }

    public static boolean correctCombo(int i, int j, int k, int[] combo) {
        if (Math.abs(combo[0] - i) <= 2 || Math.abs(combo[0] - (i - sizeOfLock)) <= 2 ||
                Math.abs((combo[0] - sizeOfLock) - i) <= 2 || Math.abs((combo[0] - sizeOfLock) - (i - sizeOfLock)) <= 2) {
            if (Math.abs(combo[1] - j) <= 2 || Math.abs(combo[1] - (j - sizeOfLock)) <= 2 ||
                    Math.abs((combo[1] - sizeOfLock) - j) <= 2 || Math.abs((combo[1] - sizeOfLock) - (j - sizeOfLock)) <= 2) {
                if (Math.abs(combo[2] - k) <= 2 || Math.abs(combo[2] - (k - sizeOfLock)) <= 2 ||
                        Math.abs((combo[2] - sizeOfLock) - k) <= 2 || Math.abs((combo[2] - sizeOfLock) - (k - sizeOfLock)) <= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void writeFile(int numOfCombos) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("combo.out"));
        text.write(numOfCombos + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(numOfCombos());
    }

}
