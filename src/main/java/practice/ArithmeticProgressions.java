/*
ID: cravuri
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.util.*;

class ariprog {

    static Set<Integer> bisquares = new HashSet<Integer>();
    static int n;
    static int m;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("ariprog.in"));
        n = Integer.parseInt(text.readLine());
        m = Integer.parseInt(text.readLine());
        for (int p = 0; p <= m; p++) {
            for (int q = p; q <= m; q++) {
                bisquares.add(p*p+q*q);
            }
        }
    }

    public static List<int[]> numOfSolutions() {
        List<int[]> solutions = new ArrayList<int[]>();
        for (int a = 0; a < 2 * m * m; a++) {
            if (!bisquares.contains(a)) {
                continue;
            }
            label:
            for(int b = 1; b <= (2 * m * m - a)/ (n - 1); b++){
                for (int i = 1; i<= n - 1; i++){
                    if(!bisquares.contains(a + b * i))
                        continue label;
                }
                solutions.add(new int[]{a, b});
            }
        }
        Collections.sort(solutions, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        return solutions;
    }

    public static void writeFile(List<int[]> solutions) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("ariprog.out"));
        if (solutions.size() == 0) {
            text.write("NONE");
            text.newLine();
            text.close();
            return;
        }
        for (int[] solution : solutions) {
            text.write(solution[0] + " " + solution[1]);
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(numOfSolutions());
    }

}
