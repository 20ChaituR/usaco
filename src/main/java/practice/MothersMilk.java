/*
ID: cravuri
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.*;

class milk3 {

    static int a;
    static int b;
    static int c;
    static List<Integer> allPoss = new ArrayList<Integer>();
    static Set<Bucket> visited = new HashSet<Bucket>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("milk3.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    public static void numOfPoss(Bucket bucket) {
        if (visited.contains(bucket)) {
            return;
        }
        if (bucket.A == 0) {
            allPoss.add(bucket.C);
        }
        visited.add(bucket);
        if (bucket.C != 0) {
            if (a - bucket.A > bucket.C) {
                numOfPoss(new Bucket(bucket.A + bucket.C, bucket.B, 0));
            } else {
                numOfPoss(new Bucket(a, bucket.B, bucket.C - (a - bucket.A)));
            }
            if (b - bucket.B > bucket.C) {
                numOfPoss(new Bucket(bucket.A, bucket.B + bucket.C, 0));
            } else {
                numOfPoss(new Bucket(bucket.A, b, bucket.C - (b - bucket.B)));
            }
        }
        if (bucket.B != 0) {
            if (a - bucket.A > bucket.B) {
                numOfPoss(new Bucket(bucket.A + bucket.B, 0, bucket.C));
            } else {
                numOfPoss(new Bucket(a, bucket.B - (a - bucket.A), bucket.C));
            }
            if (c - bucket.C > bucket.B) {
                numOfPoss(new Bucket(bucket.A, 0, bucket.B + bucket.C));
            } else {
                numOfPoss(new Bucket(bucket.A, bucket.B - (c - bucket.C), c));
            }
        }
        if (bucket.A != 0) {
            if (b - bucket.B > bucket.A) {
                numOfPoss(new Bucket(0, bucket.B + bucket.A, bucket.C));
            } else {
                numOfPoss(new Bucket(bucket.A - (b - bucket.B), b, bucket.C));
            }
            if (c - bucket.C > bucket.A) {
                numOfPoss(new Bucket(0, bucket.B, bucket.C + bucket.A));
            } else {
                numOfPoss(new Bucket(bucket.A - (c - bucket.C), bucket.B, c));
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("milk3.out"));
        Collections.sort(allPoss);
        for (int i = 0; i < allPoss.size(); i++) {
            if (i == allPoss.size() - 1) {
                text.write(allPoss.get(i) + "");
            } else {
                text.write(allPoss.get(i) + " ");
            }
        }
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        numOfPoss(new Bucket(0, 0, c));
        writeFile();
    }

    static class Bucket {
        int A;
        int B;
        int C;

        public Bucket(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bucket bucket = (Bucket) o;

            if (A != bucket.A) return false;
            if (B != bucket.B) return false;
            return C == bucket.C;

        }

        @Override
        public int hashCode() {
            int result = A;
            result = 31 * result + B;
            result = 31 * result + C;
            return result;
        }
    }

}
