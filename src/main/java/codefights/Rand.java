package codefights;/*
ID: cravuri
LANG: JAVA
TASK: Rand
*/

public class Rand {

    public static void main(String[] args) {
        int n = 100;
        int maxVal = 100;
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print((int) (maxVal*Math.random()) + (i == n - 1 ? "]" : ","));
        }
    }

}
