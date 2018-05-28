import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConvexHull {

    int cross(int[] a, int[] b) {
        return a[0] * b[1] - a[1] * b[0];
    }

    int cross(int[] a, int[] b, int[] c) {
        return cross(new int[]{a[0] - b[0], a[1] - b[1]}, new int[]{c[0] - b[0], c[1] - b[1]});
    }

    List<int[]> convexHull(List<int[]> points) {
        Collections.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int start = ans.size();
            for (int[] p : points) {
                while (ans.size() >= start + 2 && cross(p, ans.get(ans.size() - 1), ans.get(ans.size() - 2)) >= 0) {
                    ans.remove(ans.size() - 1);
                }
                ans.add(p);
            }
            Collections.reverse(points);
            ans.remove(ans.size() - 1);
        }
        return ans;
    }

}
