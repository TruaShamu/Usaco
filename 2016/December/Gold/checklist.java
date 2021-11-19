import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        Point[] hCow = new Point[H];
        Point[] gCow = new Point[G];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            hCow[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            gCow[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        long[][][] dp = new long[H + 1][G + 1][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], 1L << 60);
            }
        }
        dp[1][0][0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < 2; k++) {
                    if (k == 0 && i == 0) continue;
                    if (k == 1 && j == 0) continue;
                    Point source;
                    if (k == 0) source = hCow[i - 1];
                    else source = gCow[j - 1];
                    if (i < H) {
                        dp[i + 1][j][0] = Math.min(dp[i + 1][j][0], dp[i][j][k] + source.dist(hCow[i]));
                    }
                    if (j < G) {
                        dp[i][j + 1][1] = Math.min(dp[i][j + 1][1], dp[i][j][k] + source.dist(gCow[j]));
                    }
                }
            }
        }
        pw.println(dp[H][G][0]);
        pw.close();
    }

    static class Point {
        public int x, y;

        public Point(int a, int b) {
            x = a;
            y = b;
        }

        public int dist(Point s) {
            return (x - s.x) * (x - s.x) + (y - s.y) * (y - s.y);
        }
    }
}
