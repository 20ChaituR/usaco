package practice;
/*
 * Created by cravuri on 5/25/18
 */

public class BuildACow {

    int N, T;
    int[] edge;
    int[] station;

    int[] preEdge, sufEdge, preStat, sufStat;

    boolean canMake(int X) {
        for (int i = 0; i < X; i++) {

        }
        return true;
    }

    void solve() {
        preEdge = new int[edge.length];
        sufEdge = new int[edge.length];
        preStat = new int[edge.length];
        sufStat = new int[edge.length];

        // compute


        int lo = 0;
        int hi = N;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canMake(mid)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(lo);
    }

    public static void main(String[] args) {

    }

}
