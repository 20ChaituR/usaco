package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.dualpal
*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 8/9/15
 */
class dualpal {

    static int n;
    static int s;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("practice.dualpal.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
    }

    public static List<Integer> dualPal() {
        List<Integer> allPal = new ArrayList<Integer>();
        int i = 0;
        int k = s + 1;
        while (i < n) {
            boolean dual = false;
            for (int j = 2; j <= 10; j++) {
                if (isPalindrome(toBaseB(k, j))) {
                    if (!dual) {
                        dual = true;
                    } else {
                        allPal.add(k);
                        i++;
                        break;
                    }
                }
            }
            k++;
        }
        return allPal;
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

    public static void writeFile(List<Integer> allPal) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("practice.dualpal.out"));
        for (Integer pal : allPal) {
            text.write(pal + "");
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(dualPal());
//        n = 9;
//        s = 10;
//        System.out.println(dualPal());
    }

}
