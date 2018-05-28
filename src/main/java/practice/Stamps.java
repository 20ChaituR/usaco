/*
ID: cravuri
LANG: JAVA
TASK: stamps
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class stamps {

    static int[] price;
    static int N;
    static int K;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("stamps.in"));
        K = sc.nextInt();
        N = sc.nextInt();
        price = new int[N];
        for (int i = 0; i < N; i++) {
            price[i] = sc.nextInt();
        }
    }

    public static void maxStamps() throws IOException {
        int M = (K + 10) * 10000;
        int[] s = new int[M];
        for(int i = 0; i < M; i++) s[i] = (K + 1);
        s[0] = 0;
        for(int i = 0; i < N; i++){
            int val = price[i];
            for(int j = 0; j < M; j++){
                if(j + val < M) s[j + val] = Math.min(s[j + val], s[j] + 1);
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("stamps.out"));
        for(int i = 1; i < M; i++){
            if(s[i] > K){
                pw.println(i - 1);
                break;
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        maxStamps();
    }

}
