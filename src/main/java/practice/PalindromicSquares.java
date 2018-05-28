package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.palsquare
*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 8/9/15
 */
class palsquare {

    static int base;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("practice.palsquare.in"));
        base = Integer.parseInt(text.readLine());
    }

    public static List<IntString> findSquares() {
        List<IntString> allSquares = new ArrayList<IntString>();
        for (int i = 1; i <= 300; i++) {
            String s = toBaseB((int) Math.pow(i, 2), base);
            if (isPalindrome(s)) {
                String x = toBaseB(i, base);
                allSquares.add(new IntString(x, s));
            }
        }
        return allSquares;
    }

    public static boolean isPalindrome(String a) {
        String b = "";
        for (int i = 0; i < a.length()/2; i++) {
            b = b + a.charAt(i);
        }
        if ((a.length() % 2) == 0) {
            int j = 0;
            for (int i = a.length() - 1; i >= a.length() / 2; i--) {
                if (a.charAt(i) != b.charAt(j)) {
                    return false;
                }
                j++;
            }
        } else {
            int j = 0;
            for (int i = a.length() - 1; i > a.length() / 2; i--) {
                if (a.charAt(i) != b.charAt(j)) {
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    public static String toBaseB(int n, int b) {
        int x = n;
        String num = "";
        while (x != 0) {
            String l = (x % b) + "";
            if (l.equals("10")) {
                l = "A";

            } else if (l.equals("11")) {
                l = "B";

            } else if (l.equals("12")) {
                l = "C";

            } else if (l.equals("13")) {
                l = "D";

            } else if (l.equals("14")) {
                l = "E";

            } else if (l.equals("15")) {
                l = "F";

            } else if (l.equals("16")) {
                l = "G";

            } else if (l.equals("17")) {
                l = "H";

            } else if (l.equals("18")) {
                l = "I";

            } else if (l.equals("19")) {
                l = "J";

            } else if (l.equals("20")) {
                l = "K";

            }
            num = num + l;
            x = x / b;
        }
        return reverse(num);
    }

    public static String reverse(String n) {
        String reversed = "";
        for (int i = n.length() - 1; i >= 0; i--) {
            reversed = reversed + n.charAt(i);
        }
        return reversed;
    }

    public static void writeFile(List<IntString> allSquares) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("practice.palsquare.out"));
        for (IntString square : allSquares) {
            text.write(square.x + " " + square.s);
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(findSquares());
        System.exit(0);
//        base = 10;
//        findSquares();
    }

    static class IntString {
        String x;
        String s;

        public IntString(String x, String s) {
            this.x = x;
            this.s = s;
        }
    }
}
