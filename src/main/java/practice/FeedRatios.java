/*
ID: cravuri
LANG: JAVA
TASK: ratios
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class ratios {

    int[][] whatyouhave;
    int[] whatyouwant;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("ratios.in"));
        whatyouwant = new int[3];
        whatyouhave = new int[3][3];
        for (int i = 0; i < 3; i++) {
            whatyouwant[i] = sc.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                whatyouhave[i][j] = sc.nextInt();
            }
        }
    }

    boolean divisible(int a, int b) {
        if (b == 0) {
            return true;
        }
        return a % b == 0;
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("ratios.out"));
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k <= 100; k++) {
                    if (i == 0 && j == 0 && k == 0) {
                        continue;
                    }
                    int[] whatyouget = new int[3];
                    for (int n = 0; n < 3; n++) {
                        whatyouget[n] = i * whatyouhave[0][n] + j * whatyouhave[1][n] + k * whatyouhave[2][n];
                    }
                    if (whatyouwant[0] != 0) {
                        if (whatyouget[0] % whatyouwant[0] == 0) {
                            if (whatyouget[1] * whatyouwant[0] == whatyouget[0] * whatyouwant[1]) {
                                if (whatyouget[2] * whatyouwant[0] == whatyouget[0] * whatyouwant[2]) {
                                    pw.println(i + " " + j + " " + k + " " + (whatyouget[0] / whatyouwant[0]));
                                    pw.close();
                                    return;
                                }
                            }
                        }
                    } else if (whatyouwant[1] != 0) {
                        if (whatyouget[1] % whatyouwant[1] == 0) {
                            if (whatyouget[2] * whatyouwant[1] == whatyouget[1] * whatyouwant[2]) {
                                if (whatyouget[0] * whatyouwant[1] == whatyouget[1] * whatyouwant[0]) {
                                    pw.println(i + " " + j + " " + k + " " + (whatyouget[1] / whatyouwant[1]));
                                    pw.close();
                                    return;
                                }
                            }
                        }
                    } else if (whatyouwant[2] != 0) {
                        if (whatyouget[2] % whatyouwant[2] == 0) {
                            if (whatyouget[0] * whatyouwant[2] == whatyouget[2] * whatyouwant[0]) {
                                if (whatyouget[1] * whatyouwant[2] == whatyouget[2] * whatyouwant[1]) {
                                    pw.println(i + " " + j + " " + k + " " + (whatyouget[2] / whatyouwant[2]));
                                    pw.close();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.println("NONE");
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        ratios prob = new ratios();
        prob.readFile();
        prob.solve();
    }

}
