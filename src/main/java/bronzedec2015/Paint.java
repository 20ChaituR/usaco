package bronzedec2015;/*
ID: cravuri
LANG: JAVA
TASK: bronzedec2015.Paint
*/

import java.io.*;
import java.util.StringTokenizer;

public class Paint {

    static int a;
    static int b;
    static int c;
    static int d;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("paint.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(text.readLine());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
    }

    public static int fenceLength () {
        if (a < c) {
            if (c > b) {
                return (b - a) + (d - c);
            } else if (c == b) {
                return (b - a) + (d - c);
            } else {
                if (b < d) {
                    return (d - a);
                } else if (b == d) {
                    return (d - a);
                } else {
                    return (b - a);
                }
            }
        } else if (a == c) {
            if (b < d) {
                return (d - a);
            } else if (b == d) {
                return (d - a);
            } else {
                return (b - a);
            }
        } else {
            if (a > d) {
                return (d - c) + (b - a);
            } else if (a == d) {
                return (d - c) + (b - a);
            } else {
                if (d < b) {
                    return (b - c);
                } else if (d == b) {
                    return (b - c);
                } else {
                    return (d - c);
                }
            }
        }
    }

    public static void writeFile(int length) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("paint.out"));
        text.write(length + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
//        a = 1;
//        b = 3;
//        c = 3;
//        d = 6;
//        System.out.println(fenceLength());
        readFile();
        writeFile(fenceLength());
    }
}
