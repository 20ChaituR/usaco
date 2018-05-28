import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class COWBASIC {

    static final int MOD = 1000000007;
    static BufferedReader b;
    static PrintWriter pw;
    static Map<String, Integer> variables = new HashMap<>();
    static List<String> lines = new ArrayList<>();

    public static boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static void runExpression(int i, int j) {
        if (i > j) {
            return;
        }
        StringTokenizer st = new StringTokenizer(lines.get(i));
        String a = st.nextToken();
        if (!isNumeric(a)) {
            if (a.equals("RETURN")) {
                String v = st.nextToken();
                pw.println(variables.get(v));
            } else { // set variable
                int x = 0;
                String next;
                while (st.hasMoreElements()) {
                    next = st.nextToken();
                    if (next.equals("(") || next.equals(")") || next.equals("+") || next.equals("=")) {
                        continue;
                    }
                    if (isNumeric(next)) {
                        x += Integer.parseInt(next);
                    } else {
                        x += variables.get(next);
                    }
                    x %= MOD;
                }
                variables.put(a, x % MOD);
                runExpression(i + 1, j);
            }
        } else { // mooloop
            int literal = Integer.parseInt(a);
            int balanced = 1;
            int end = -1;
            for (int k = i + 1; ; k++) {
                if (isNumeric(lines.get(k).split(" ")[0])) {
                    balanced++;
                }
                if (lines.get(k).split(" ")[0].equals("}")) {
                    balanced--;
                }
                if (balanced == 0) {
                    end = k;
                    break;
                }
            }
            for (int k = 0; k < literal; k++) {
                runExpression(i + 1, end - 1);
            }
            runExpression(end + 1, j);
        }
    }

    public static void main(String[] args) throws IOException {
        b = new BufferedReader(new FileReader("cowbasic.in"));
        pw = new PrintWriter(new FileWriter("cowbasic.out"));
        String d;
        while ((d = b.readLine()) != null) {
            lines.add(d);
        }
        runExpression(0, lines.size() - 1);
        pw.close();
    }

}
