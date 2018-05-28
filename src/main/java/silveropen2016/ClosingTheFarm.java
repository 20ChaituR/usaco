package silveropen2016;

import java.io.*;
import java.util.*;

public class ClosingTheFarm {

    static Map<Integer, List<Integer>> paths = new HashMap<>();
    static int[] closing;
    static boolean[] possible;
    static Set<Integer> visited = new HashSet<>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("closing.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        closing = new int[n];
        possible = new boolean[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (paths.containsKey(a)) {
                paths.get(a).add(b);
            } else {
                List<Integer> connect = new ArrayList<>();
                connect.add(b);
                paths.put(a, connect);
            }
            if (paths.containsKey(b)) {
                paths.get(b).add(a);
            } else {
                List<Integer> connect = new ArrayList<>();
                connect.add(a);
                paths.put(b, connect);
            }
        }
        for (int i = 0; i < n; i++) {
            closing[i] = Integer.parseInt(text.readLine());
        }
    }

    public static void solve() {
        possible[0] = isConnected();
        for (int i = 0; i < closing.length - 1; i++) {
            visited = new HashSet<>();
            paths.remove(closing[i]);
            possible[i + 1] = isConnected();
        }
    }

    public static boolean isConnected() {
        travel(closing[closing.length - 1]);
        return visited.size() == paths.size();
    }

    public static void travel(int curr) {
        if (visited.contains(curr)) {
            return;
        }
        if (!paths.containsKey(curr)) {
            return;
        }
        visited.add(curr);
        List<Integer> connect = paths.get(curr);
        for (Integer barn : connect) {
            travel(barn);
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("closing.out"));
        for (boolean element : possible) {
            if (element) {
                text.write("YES");
            } else {
                text.write("NO");
            }
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        solve();
        writeFile();
    }

}
