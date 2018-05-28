/*
ID: cravuri
LANG: JAVA
TASK: contact
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class contact {

    int A, B, N;
    String bits;

    void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("contact.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
//        bits = text.readLine();
        StringBuilder b = new StringBuilder();
        String ln;
        while ((ln = text.readLine()) != null) {
            b.append(ln);
        }
        bits = b.toString();
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("contact.out"));
        HashMap<String, Integer> frequencies = new HashMap<>();
        TreeSet<String>[] times = new TreeSet[bits.length() + 2];
        for (int i = 0; i < times.length; i++) {
            times[i] = new TreeSet<>();
        }
        for (int len = A; len <= B; len++) {
            for (int i = 0; i + len <= bits.length(); i++) {
                String cur = bits.substring(i, i + len);
                if (frequencies.containsKey(cur)) {
                    times[frequencies.get(cur)].remove(cur);
                    frequencies.put(cur, frequencies.get(cur) + 1);
                    times[frequencies.get(cur)].add(cur);
                } else {
                    frequencies.put(cur, 1);
                    times[1].add(cur);
                }
            }
        }
        for (int i = times.length - 1, num = 0; i >= 1 && num < N; i--) {
            if (!times[i].isEmpty()) {
                pw.println(i);
                String[] patterns = new String[times[i].size()];
                int j = 0;
                for (String s : times[i]) {
                    patterns[j] = s;
                    j++;
                }
                Arrays.sort(patterns, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (o1.length() == o2.length()) {
                            return o1.compareTo(o2);
                        } else {
                            return o1.length() - o2.length();
                        }
                    }
                });
                for (j = 0; j < patterns.length; j++) {
                    if (j % 6 == 0) {
                        if (j == patterns.length - 1) {
                            pw.println(patterns[j]);
                        } else {
                            pw.print(patterns[j]);
                        }
                    } else if (j == patterns.length - 1 || j % 6 == 5) {
                        pw.println(" " + patterns[j]);
                    } else {
                        pw.print(" " + patterns[j]);
                    }
                }
                num++;
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        contact prob = new contact();
        prob.readFile();
        prob.solve();
    }

}
