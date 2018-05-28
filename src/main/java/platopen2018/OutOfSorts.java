/*
 * Created by cravuri on 3/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutOfSorts {

    int N;
    int[] A;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("sort.in"));
        N = sc.nextInt();
        A = new int[N + 1];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        A[N] = (int) 1e9 + 3;
    }

    int wc = 0;

    void bspass(int[] A, int l, int r) {
        for (int i = l; i < r - 1; i++) {
            if (A[i] > A[i + 1]) {
                int temp = A[i];
                A[i] = A[i + 1];
                A[i + 1] = temp;
            }
        }
    }

    void quickSort(int[] A, int l, int r) {
        if (r - l == 1) {
            return;
        }
        wc += r - l;
        bspass(A, l, r);
        int curMax = 0;
        List<Integer> part = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] > curMax) {
                curMax = A[i];
                part.add(i);
            }
        }
        part.add(N);
        for (int i = 0; i < part.size() - 1; i++) {
            quickSort(A, part.get(i), part.get(i + 1));
        }
    }

    void solve() throws IOException {
        quickSort(A, 0, N);
        PrintWriter pw = new PrintWriter(new FileWriter("sort.out"));
        pw.println(wc);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        OutOfSorts prob = new OutOfSorts();
        prob.readFile();
        prob.solve();
    }

}
