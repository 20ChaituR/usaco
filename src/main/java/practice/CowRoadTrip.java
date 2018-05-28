package practice;
/*
 * Created by cravuri on 5/1/18
 */

import java.util.ArrayList;
import java.util.List;

public class CowRoadTrip {

    int N;
    List<Integer>[] adj;

    void readFile() {

    }

    List<Child>[] children;

    int[] numInc, numDec;

    int getIncLength(int root) {
        return 0;
    }

    int getDecLength(int root) {
        return 0;
    }

    void solve() {
        numInc = new int[N];
        numDec = new int[N];
        for (int i = 0; i < N; i++) {
            numInc[i] = -1;
            numDec[i] = -1;
        }

        getIncLength(0);
        getDecLength(0);

        int ans = 0;
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < children[k].size(); i++) {
                for (int j = 0; j < children[k].size(); j++) {
                    if (i != j) {
                        ans += children[k].get(i).decLength * children[k].get(j).incLength;
                        ans += children[k].get(i).incLength * children[k].get(j).decLength;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {

    }

    class Child {
        int ind;
        int incLength;
        int decLength;
    }

}
