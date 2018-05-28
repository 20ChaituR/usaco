import java.util.*;

public class Uber1 {

    int s = 1;
    int a_bit_bit_cnt(int n) {
        int c = 0;
        while (n > 0) {
            c++;
            n &= n - 1;
        }
        s++;
        s += c;
        return c > 1 ? a_bit_bit_cnt(c) : s;
    }

    public static void main(String[] args) {
        Uber1 prob = new Uber1();
//        System.out.println(prob.a_bit_bit_cnt(3));
        for (int i = 0; i < 20; i++) {
            System.out.println(i + " : " + prob.a_bit_bit_cnt(i));
        }
    }
}