/*
ID: cravuri
LANG: JAVA
TASK: msquare
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class msquare2 {

    int[] perm = new int[8];
    int[] end = new int[8];

    int[] A(int[] perm) {
        int[] temp = new int[8];
        temp[0] = perm[7];
        temp[1] = perm[6];
        temp[2] = perm[5];
        temp[3] = perm[4];
        temp[4] = perm[3];
        temp[5] = perm[2];
        temp[6] = perm[1];
        temp[7] = perm[0];
        return temp;
    }

    int[] B(int[] perm) {
        int[] temp = new int[8];
        temp[0] = perm[3];
        temp[1] = perm[0];
        temp[2] = perm[1];
        temp[3] = perm[2];
        temp[4] = perm[5];
        temp[5] = perm[6];
        temp[6] = perm[7];
        temp[7] = perm[4];
        return temp;
    }

    int[] C(int[] perm) {
        int[] temp = new int[8];
        temp[0] = perm[0];
        temp[1] = perm[6];
        temp[2] = perm[1];
        temp[3] = perm[3];
        temp[4] = perm[4];
        temp[5] = perm[2];
        temp[6] = perm[5];
        temp[7] = perm[7];
        return temp;
    }

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("msquare.in"));
        for (int i = 1; i <= 8; i++) {
            perm[i - 1] = i;
        }
        for (int i = 0; i < 8; i++) {
            end[i] = sc.nextInt();
        }
        if (end(perm)) {
            System.out.println(0);
            System.exit(0);
        }
        int t = perm[1];
        perm[1] = perm[0];
        perm[0] = t;
        t = perm[3];
        perm[3] = perm[2];
        perm[2] = t;
    }

    boolean end(int[] perm) {
        for (int i = 0; i < perm.length; i++) {
            if (perm[i] != end[i]) {
                return false;
            }
        }
        return true;
    }

    boolean equals(int[] p1, int[] p2) {
        for (int i = 0; i < p1.length; i++) {
            if (p1[i] != p2[i]) {
                return false;
            }
        }
        return true;
    }

//    void dfs(int[] perm, List<int[]> visited, int length, String transform) {
//        if (end(perm)) {
//            if (length < minLength) {
//                minLength = length;
//                minTransform = transform;
//            }
//            return;
//        }
//        for (int[] p : visited) {
//            if (equals(p, perm)) {
//                return;
//            }
//        }
//        visited.add(perm);
//        dfs(A(perm), visited, length + 1, transform + "A");
//        dfs(B(perm), visited, length + 1, transform + "B");
//        dfs(C(perm), visited, length + 1, transform + "C");
//    }

    String bfs() {
        Queue<State> q = new ArrayDeque<>();
        Set<State> visited = new HashSet<>();
        q.add(new State(perm, ""));
        visited.add(new State(perm, ""));
        String minTransform = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        while (!q.isEmpty()) {
            State s = q.remove();
            if (end(s.p)) {
                minTransform = minTransform.length() >= s.transform.length() ? minTransform.compareTo(s.transform) > 0 ? s.transform : minTransform : minTransform;
                continue;
            }
            if (s.transform.length() > 22) {
                return minTransform;
            }
            int[] a = A(s.p);
            int[] b = B(s.p);
            int[] c = C(s.p);
            if (!visited.contains(new State(a, ""))) {
                q.add(new State(a, s.transform + "A"));
                visited.add(new State(a, s.transform + "A"));
            }
            if (!visited.contains(new State(b, ""))) {
                q.add(new State(b, s.transform + "B"));
                visited.add(new State(b, s.transform + "B"));
            }
            if (!visited.contains(new State(c, ""))) {
                q.add(new State(c, s.transform + "C"));
                visited.add(new State(c, s.transform + "C"));
            }
        }
        return minTransform;
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("msquare.out"));
        String minTransform = bfs();
        pw.println(minTransform.length());
        for (int i = 0; i < minTransform.length(); i++) {
            pw.print(minTransform.charAt(i));
            if (i % 60 == 59) {
                pw.println();
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        msquare2 prob = new msquare2();
        prob.readFile();
        prob.solve();
    }

    class State {
        int[] p;
        String transform;

        public State(int[] p, String transform) {
            this.p = p;
            this.transform = transform;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            return Arrays.equals(p, state.p);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(p);
        }
    }

}
