/*
 * Created by cravuri on 5/25/18
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CowFavoritism {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        HashMap<String, List<String>> mp = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String type = sc.next();
            if (type.charAt(0) == 'B') {
                String id = sc.next();
                String name = sc.next();
                if (!mp.containsKey(name)) {
                    mp.put(name, new ArrayList<String>());
                }
                mp.get(name).add(id);
            } else if (type.charAt(0) == 'Q') {
                int k = sc.nextInt();
                String name = sc.next();
                System.out.println(mp.get(name).get(k - 1));
            } else if (type.charAt(0) == 'S') {
                String name = sc.next();
                mp.get(name).remove(mp.get(name).size() - 1);
            }
        }
    }

}
