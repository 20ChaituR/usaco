package practice;
/*
 * Created by cravuri on 5/3/18
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CowSpots {

    int N;
    int[][] circles;

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        circles = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                circles[i][j] = sc.nextInt();
            }
        }
    }

    int distance(int[] c1, int[] c2) {
        return (c1[0] - c2[0]) * (c1[0] - c2[0]) + (c1[1] - c2[1]) * (c1[1] - c2[1]);
    }

    void solve() {
        Set<int[]> active = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        for (int i = 0; i < N; i++) {
            int area = circles[i][2]*circles[i][2];
            Iterator<int[]> iter = active.iterator();
            boolean found = true;
            while (iter.hasNext()) {
                int[] circle = iter.next();
                if (distance(circle, circles[i]) <= circles[i][2]*circles[i][2] || distance(circle, circles[i]) <= circle[2]*circle[2]) {
                    if (circle[2] > circles[i][2]) {
                        area = 0;
                        found = false;
                        break;
                    } else {
                        area -= circle[2] * circle[2];
                        iter.remove();
                    }
                }
            }
            if (found) {
                active.add(circles[i]);
            }
            System.out.println(area);
        }
    }

    public static void main(String[] args) {
        CowSpots prob = new CowSpots();
        prob.readFile();
        prob.solve();
    }

}
