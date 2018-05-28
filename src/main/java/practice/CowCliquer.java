package practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CowCliquer extends Grader {
    // Copy these exactly:
    public static void main(String args[]) throws IOException {
        new CowCliquer().run();
    }

    @Override
    public Grader newInstance() {
        return new CowCliquer();
    }

    public HashMap<String, String> parent = new HashMap<String, String>();
    public HashMap<String, Integer> size = new HashMap<String, Integer>();
    public HashMap<String, Integer> timeP = new HashMap<String, Integer>();
    HashMap<String, List<Integer>> times = new HashMap<>();
    HashMap<String, List<Integer>> sizes = new HashMap<>();

    public String findAncestor(String a, int timestamp) {
        if (parent.get(a).equals(a)) {
            return a;
        }
        if (timeP.containsKey(a) && timeP.get(a) > timestamp) {
            return a;
        }
        String p = parent.get(a);
//        parent.put(a, parent.get(p));
        return findAncestor(p, timestamp);
    }

    public void union(String x, String y, int timestamp) {
        String a = findAncestor(x, timestamp);
        String b = findAncestor(y, timestamp);
        if (a.equals(b)) {
            return;
        }
        int sa = size.get(a);
        int sb = size.get(b);
        if (sa > sb) {
            parent.put(b, a);
            size.put(a, sa + sb);
            timeP.put(b, timestamp);
            if (!times.containsKey(a)) {
                times.put(a, new ArrayList<Integer>());
                sizes.put(a, new ArrayList<Integer>());
            }
            times.get(a).add(timestamp);
            sizes.get(a).add(sa + sb);

        } else {
            parent.put(a, b);
            size.put(b, sa + sb);
            timeP.put(a, timestamp);
            if (!times.containsKey(b)) {
                times.put(b, new ArrayList<Integer>());
                sizes.put(b, new ArrayList<Integer>());
            }
            times.get(b).add(timestamp);
            sizes.get(b).add(sa + sb);
        }
    }

    public void makeSet(String a, int timestamp) {
        parent.put(a, a);
        size.put(a, 1);
    }

    // Implement these:
    @Override
    public void addFriend(String cow1, String cow2, int timestamp) {
        if (!parent.containsKey(cow1)) {
            makeSet(cow1, timestamp);
        }
        if (!parent.containsKey(cow2)) {
            makeSet(cow2, timestamp);
        }
        union(cow1, cow2, timestamp);
    }

    @Override
    public boolean checkFriend(String cow1, String cow2, int timestamp) {
        if (!parent.containsKey(cow1) || !parent.containsKey(cow2)) {
            return false;
        }
        String a = findAncestor(cow1, timestamp);
        String b = findAncestor(cow2, timestamp);
        return a.equals(b);
    }

    @Override
    public int getNumberOfFriends(String cow, int timestamp) {
        if (!parent.containsKey(cow)) {
            return 1;
        }
        String a = findAncestor(cow, timestamp);
        if (!times.containsKey(a)) {
            return 1;
        }
        List<Integer> time = times.get(a);
        int lo = 0;
        int hi = time.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (time.get(mid) < timestamp) {
                lo = mid + 1;
            } else if (time.get(mid) > timestamp) {
                hi = mid - 1;
            } else {
                hi = mid;
                break;
            }
        }
        if (hi < 0) {
            return 1;
        }
        return sizes.get(a).get(hi);
//        return size.get(findAncestor(cow, timestamp));
    }
}