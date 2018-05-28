import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PromotionCounting {

    int N;
    int[] rating;
    int[] parent;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("promote.in"));
        N = sc.nextInt();
        rating = new int[N];
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            rating[i] = sc.nextInt();
        }
        parent[0] = -1;
        for (int i = 1; i < N; i++) {
            parent[i] = sc.nextInt() - 1;
        }
    }

    void solve() throws IOException {
        int[] promotions = new int[N];
        for (int i = 0; i < N; i++) {
            int cur = i;
            while (parent[cur] != -1) {
                cur = parent[cur];
                if (rating[cur] < rating[i]) {
                    promotions[cur]++;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("promote.out"));
        for (int i = 0; i < promotions.length; i++) {
            pw.println(promotions[i]);
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        PromotionCounting prob = new PromotionCounting();
        prob.readFile();
        prob.solve();
    }

}
