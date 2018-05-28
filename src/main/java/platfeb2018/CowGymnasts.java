/*
 * Created by cravuri on 2/25/18
 */

import java.util.Arrays;

public class CowGymnasts {

    int N;
    int[] stacks;
    int count = 0;

    static boolean works(int[] stacks) {
        int[] nStacks = new int[stacks.length];
        for (int j = 0; j < stacks.length; j++) {
            for (int k = 0; k < stacks[j]; k++) {
                nStacks[(j + k) % stacks.length]++;
            }
        }
        for (int j = 0; j < stacks.length; j++) {
            if (nStacks[j] != stacks[j]) {
                return false;
            }
        }
        return true;
    }

    void makeStacks(int i) {
        if (i == N) {
            int[] nStacks = new int[N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < stacks[j]; k++) {
                    nStacks[(j + k) % N]++;
                }
            }
            for (int j = 0; j < N; j++) {
                if (nStacks[j] != stacks[j]) {
                    return;
                }
            }
            System.out.println(Arrays.toString(stacks));
            count++;
            return;
        }
        for (int j = 1; j <= N; j++) {
            stacks[i] = j;
            makeStacks(i + 1);
        }
    }

    void solve() {
        stacks = new int[N];
        makeStacks(0);
        System.out.println(count);
    }

    public static void main(String[] args) {
        CowGymnasts prob = new CowGymnasts();
        System.out.println(works(new int[]{6,4,4,4,4,6,4,4,4,4}));
    }

}
