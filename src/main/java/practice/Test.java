package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.test
*/
import java.io.*;
import java.util.*;

class test {

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("practice.test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("practice.test.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());
        out.println(i1+i2);
        out.close();
        System.exit(0);
    }

}
