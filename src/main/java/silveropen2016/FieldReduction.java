package silveropen2016;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FieldReduction {

    static List<Point> cows = new ArrayList<>();
    static double N;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("reduce.in"));
        N = Double.parseDouble(text.readLine());
        for (int i = 0; i < N; i++) {
            Point p = new Point();
            StringTokenizer st = new StringTokenizer(text.readLine());
            p.x = Double.parseDouble(st.nextToken());
            p.y = Double.parseDouble(st.nextToken());
            cows.add(p);
        }
    }

    public static int minArea() {
        for (int count = 0; count < 3; count++) {
            double avgX = 0;
            double avgY = 0;
            for (Point cow : cows) {
                avgX += cow.x;
                avgY += cow.y;
            }
            avgX = avgX / N;
            avgY = avgY / N;
            Point p = new Point();
            p.x = avgX;
            p.y = avgY;
            double maxDistance = -1;
            int k = -1;
            for (int i = 0; i < cows.size(); i++) {
                double x = p.distance(cows.get(i));
                if (x > maxDistance) {
                    maxDistance = x;
                    k = i;
                }
            }
            cows.remove(k);
        }
        double minX = 40001;
        double maxX = 0;
        double minY = 40001;
        double maxY = 0;
        for (Point cow : cows) {
            if (cow.x > maxX) {
                maxX = cow.x;
            }
            if (cow.x < minX) {
                minX = cow.x;
            }
            if (cow.y > maxY) {
                maxY = cow.y;
            }
            if (cow.y < minY) {
                minY = cow.y;
            }
        }
        return (int) ((maxX - minX) * (maxY - minY));
    }

    public static void writeFile(int x) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("reduce.out"));
        text.write(x + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minArea());
    }

    static class Point {
        double x;
        double y;

        public double distance(Point p) {
            return Math.abs(x - p.x) + Math.abs(y - p.y);
        }
    }

}
