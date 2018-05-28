package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.friday
*/
import java.io.*;
/**
 * 7/23/15
 */
class friday {

    static int numOfYears;
    static int[] count = new int[7];

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("practice.friday.in"));
        numOfYears = Integer.parseInt(text.readLine());
    }

    public static boolean isLeapYear(int year) {
        return ((year % 400) == 0) || (((year % 100) != 0) && ((year % 4) == 0));
    }

    public static void rec(int n, int day) {
        if (n >= numOfYears) {
            return;
        }
        int date = day;
        for (int i = 1; i <= 12; i++) {
            date += 12;
            count[date % 7]++;
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                date += 19;
            } else if (i == 2) {
                if (isLeapYear(1900 + n)) {
                    date += 17;
                } else {
                    date += 16;
                }
            } else {
                date += 18;
            }
        }
        rec(n + 1, (date) % 7);
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("practice.friday.out"));
        for (int i = 0; i < count.length - 1; i++) {
            text.write(count[i] + " ");
        }
        text.write(count[6] + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        rec(0, 2);
        writeFile();
        System.exit(0);
    }

}
