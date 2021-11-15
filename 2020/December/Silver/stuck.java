import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem2 {
    public static int[][] cum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //Read in each point.
        point[] points = new point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new point(x, y);
        }
        Arrays.sort(points);

        cum = new int[5001][5001];
        for (point oPoint : points) {
            cum[oPoint.x + 1][oPoint.y + 1]++;
        }

        for (int i = 1; i < (N + 1); i++) {
            for (int j = 1; j < (N + 1); j++) {
                cum[i][j] += cum[i - 1][j] + cum[i][j - 1] - cum[i - 1][j - 1];
            }
        }

        long ans = 1;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int hi = Integer.max(points[i].y, points[j].y);
                int lo = Integer.min(points[i].y, points[j].y);
                ans += get_sum(i, j, hi, N - 1) * get_sum(i, j, 0, lo);
            }
        }
        System.out.println("ANSWER: " + ans);

    }

    public static int get_sum(int xLeft, int xRight, int yLeft, int yRight) {
        ++xRight;
        ++yRight;
        return cum[xRight][yRight] - cum[xLeft][yRight] - cum[xRight][yLeft] + cum[xLeft][yLeft];
    }

    static class point implements Comparable<point> {
        public int x;
        public int y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(point other) {
            if (this.x != other.x) {
                return Integer.compare(this.x, other.x);
            }
            return Integer.compare(this.y, other.y);
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}

