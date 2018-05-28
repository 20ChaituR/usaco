/*
 * Created by cravuri on 2/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class CowGymnasts2 {

    long N;

    final long MOD = (long) (1e9) + 7;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("gymnasts.in"));
        N = sc.nextLong();
    }

    long pow(long a, long b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a % MOD;
        }
        long x = pow(a, b / 2) % MOD;
        if (b % 2 == 0) {
            return (x * x) % MOD;
        } else {
            return (x * x * a) % MOD;
        }
    }

    static int gcd(long a, long b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    void solve2() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("gymnasts.out");
//        if (N % 2 == 1) {
//            pw.println(N % MOD);
//            pw.close();
//            return;
//        }
        long x = N % MOD;
        for (int i = 1; i < N; i++) {
            x += (pow(2, gcd(i, N)) + MOD - 2) % MOD;
            x %= MOD;
        }
        pw.println(x % MOD);
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        CowGymnasts2 prob = new CowGymnasts2();
        prob.readFile();
        prob.solve2();
    }

}
