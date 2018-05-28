import java.util.*;
import java.io.*;
import java.math.*;

public class CodinGame3 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        Queue<Integer> p1 = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            p1.add((int) cardp1.charAt(0));
        }
        int m = in.nextInt(); // the number of cards for player 2
        Queue<Integer> p2 = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            p2.add((int) cardp2.charAt(0));
        }
        boolean win = true;
        int count = 0;
        while (!p1.isEmpty() || !p2.isEmpty()) {
            int a = p1.remove();
            int b = p2.remove();
            if (a > b) {
                p1.add(a);
                p1.add(b);
                count++;
            }
            if (b > a) {
                p2.add(b);
                p2.add(a);
                count++;
            }
            if (a == b) {
                List<Integer> pl1 = new ArrayList<>();
                List<Integer> pl2 = new ArrayList<>();
                pl1.add(a);
                pl2.add(b);
                while (a == b) {
                    for (int i = 0; i < 3; i++) {
                        pl1.add(p1.remove());
                        pl2.add(p2.remove());
                    }
                    a = p1.remove();
                    b = p2.remove();
                }
                count++;
            }
        }
    }
}
