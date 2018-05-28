/*
ID: cravuri
LANG: JAVA
TASK: skidesign
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class skidesign {

    static int[] hillHeights;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("skidesign.in"));
        hillHeights = new int[Integer.parseInt(text.readLine())];
        for (int i = 0; i < hillHeights.length; i++) {
            hillHeights[i] = Integer.parseInt(text.readLine());
        }
    }

    public static int minMoney() {
        int max = maxElement(hillHeights);
        List<Integer> possValues = new ArrayList<Integer>();
        for (int i = 17; i <= max; i++) {
            int x = 0;
            for (int height : hillHeights) {
                if (height < i - 17) {
                    x += squared(i - 17 - height);
                } else if (height > i) {
                    x += squared(height - i);
                }
            }
            possValues.add(x);
        }
        return minElement(possValues);
    }

    public static void equals(int[] a, int[] b) {
        for (int i = 0; i < b.length; i++) {
            a[i] = b[i];
        }
    }

    public static int squared(int a) {
        return a*a;
    }

    public static int maxElement(int[] a) {
        int x = Integer.MIN_VALUE;
        for (int element : a) {
            if (element > x) {
                x = element;
            }
        }
        return x;
    }

    public static int minElement(List<Integer> a) {
        int x = Integer.MAX_VALUE;
        for (Integer element : a) {
            if (element < x) {
                x = element;
            }
        }
        return x;
    }

    public static void writeFile(int x) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("skidesign.out"));
        text.write(x + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minMoney());
    }

}
