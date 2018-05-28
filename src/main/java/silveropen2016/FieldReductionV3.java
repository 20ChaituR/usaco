package silveropen2016;/*
ID: cravuri
LANG: JAVA
TASK: silveropen2016.FieldReductionV3
*/

import java.io.*;
import java.util.*;

public class FieldReductionV3 {

//    static List<Point> cowsX = new ArrayList<>();
//    static List<Point> cowsY = new ArrayList<>();
//    static int N;
//
//    public static void readFile() throws IOException {
//        BufferedReader text = new BufferedReader(new FileReader("reduce.in"));
//        N = Integer.parseInt(text.readLine());
//        for (int i = 0; i < N; i++) {
//            Point p = new Point();
//            StringTokenizer st = new StringTokenizer(text.readLine());
//            p.x = Integer.parseInt(st.nextToken());
//            p.y = Integer.parseInt(st.nextToken());
//            cowsX.add(p);
//            cowsY.add(p);
//        }
//        Collections.sort(cowsX, new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                return o1.x - o2.x;
//            }
//        });
//        Collections.sort(cowsY, new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                return o1.y - o2.y;
//            }
//        });
//    }
//
//    public static int minArea(int count, List<Point> points) {
//        if (count >= 3) {
//            return findArea(points);
//        }
//        Point maxX = cowsX.get(cowsX.size() - 1);
//
//    }
//
//    public static void equals(List<Point> a, List<Point> b) {
//        for (Point p : a) {
//            b.add(p);
//        }
//    }
//
//    public static int findArea(List<Point> points) {
//        int minX;
//        boolean xMin;
//        int minY;
//        boolean yMin;
//        int maxX;
//        boolean xMax;
//        int maxY;
//        boolean yMax;
//        for (Point p : points) {
//            if (cowsX.get(cowsX.size() - 1) == p) {
//                xMax = true;
//                maxX = cowsX.get(cowsX.size() - 2).x;
//            } else {
//                maxX = cowsX.get(cowsX.size() - 1).x;
//            }
//            if (cowsX.get(0) == p) {
//                minX = cowsX.get(1).x;
//            } else {
//                minX = cowsX.get(0).x;
//            }
//            if (cowsY.get(cowsY.size() - 1) == p) {
//                maxY = cowsY.get(cowsY.size() - 2).y;
//            } else {
//                maxY = cowsY.get(cowsY.size() - 1).y;
//            }
//            if (cowsY.get(0) == p) {
//                minY = cowsY.get(1).y;
//            } else {
//                minY = cowsY.get(0).y;
//            }
//        }
//        return (maxX - minX) * (maxY - minY);
//    }
//
//    public static void writeFile(int x) throws IOException {
//        BufferedWriter text = new BufferedWriter(new FileWriter("reduce.out"));
//        text.write(x + "");
//        text.newLine();
//        text.close();
//    }
//
//    public static void main(String[] args) throws IOException {
//        readFile();
//        writeFile(minArea());
//    }
//
//    static class Point {
//        int x;
//        int y;
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            Point point = (Point) o;
//
//            if (x != point.x) return false;
//            return y == point.y;
//
//        }
//
//        @Override
//        public int hashCode() {
//            int result = x;
//            result = 31 * result + y;
//            return result;
//        }
//    }

}
