package platopen2018;

import java.io.IOException;

// If you find it necessary, you may import other standard libraries here.

public class Bessie extends Grader {
    // Copy this exactly:
    public static void main(String args[]) throws IOException {
        new Bessie().run();
    }

    @Override
    public void helpBessie(int ID) {
        int K = getWindowLength();
        int N = getTrainLength();
        int ind = getCurrentCarIndex();
        int pass = getCurrentPassIndex();
        int rn = 200;
        if (ID == 0) {
            ID = -1;
        }
        if (pass == 0) {
            set(ind / rn, Math.min(get(ind / rn), ID));
        }
        if (pass == 1) {
            if (ind >= K - 1) {
                if (ind % K <= 200) {
                    set(5000 + (ind % K), ID);
                } else if (K - (ind % K) <= 200) {
                    set(5499 - (ind % K), ID);
                }
                int l = ind - K;
                int r = ind;
                int a = l / rn;
                int b = r / rn;
                int min = Integer.MAX_VALUE;
                for (int i = a + 1; i < b; i++) {
                    int val = get(i);
                    if (val != 0) {
                        min = Math.min(min, val);
                    }
                }
                for (int i = 5000; i <= 5499; i++) {
                    int val = get(i);
                    if (val != 0) {
                        min = Math.min(min, val);
                    }
                }
                if (min == -1) {
                    shoutMinimum(0);
                } else {
                    shoutMinimum(min);
                }
            } else {
                if (ind <= 200) {
                    set(5000 + ind, ID);
                } else if (K - ind <= 200) {
                    set(5499 - ind, ID);
                }
            }
        }
    }

}