import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RoboticCowHerd {

    int N, K;
    List<Integer>[] controllers;
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("roboherd.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        controllers = new List[N];
        for (int i = 0; i < N; i++) {
            controllers[i] = new ArrayList<>();
            int M = sc.nextInt();
            for (int j = 0; j < M; j++) {
                controllers[i].add(sc.nextInt());
            }
        }
    }

    void dfs(int i, int sum) {
        if (i == N) {
            pq.add(sum);
            return;
        }
        for (int j = 0; j < controllers[i].size(); j++) {
            dfs(i + 1, sum + controllers[i].get(j));
        }
    }

    void solve() throws IOException {
        for (int i = 0; i < controllers.length; i++) {
            Collections.sort(controllers[i]);
        }
        dfs(0, 0);
        int cost = 0;
        for (int i = 0; i < K; i++) {
            cost += pq.remove();
        }
        PrintWriter pw = new PrintWriter(new FileWriter("roboherd.out"));
        pw.println(cost);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        RoboticCowHerd prob = new RoboticCowHerd();
        prob.readFile();
        prob.solve();
    }

}
