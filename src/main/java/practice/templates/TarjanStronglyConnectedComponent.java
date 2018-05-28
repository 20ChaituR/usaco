import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TarjanStronglyConnectedComponent {

    int N;
    List<List<Integer>> adj = new ArrayList<>();

    void readFile() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N; i++) {
            while (true) {
                int a = sc.nextInt();
                if (a == 0) {
                    break;
                }
                adj.get(i).add(a - 1);
            }
        }
    }

    int cur = 0;
    int[] index;
    int[] lowlink;
    boolean[] onStack;
    List<List<Integer>> components = new ArrayList<>();
    Stack<Integer> s = new Stack<>();

    void tarjan() {
        index = new int[N];
        lowlink = new int[N];
        onStack = new boolean[N];
        for (int i = 0; i < N; i++) {
            index[i] = -1;
            lowlink[i] = -1;
        }
        for (int i = 0; i < N; i++) {
            if (index[i] == -1) {
                strongConnect(i);
            }
        }
        int x = 0;
    }

    void strongConnect(int v) {
        index[v] = cur;
        lowlink[v] = cur;
        cur++;
        s.add(v);
        onStack[v] = true;
        for (int w : adj.get(v)) {
            if (index[w] == -1) {
                strongConnect(w);
            }
            lowlink[v] = Math.min(lowlink[v], index[w]);
        }
        if (lowlink[v] == index[v]) {
            components.add(new ArrayList<Integer>());
            int w = -1;
            do {
                w = s.pop();
                onStack[w] = false;
                components.get(components.size() - 1).add(w);
            } while (w != v);
        }
    }

    public static void main(String[] args) {
        TarjanStronglyConnectedComponent prob = new TarjanStronglyConnectedComponent();
        prob.readFile();
        prob.tarjan();
    }

}
