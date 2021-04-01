import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class moop {
    public static point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        int N = Integer.parseInt(br.readLine());
        points = new point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i].x + " " + points[i].y);
        }
        int[] minL = new int[N];
        int[] maxR = new int[N];

        minL[0] = points[0].y;


        for (int i = 1; i < N; i++) {
            minL[i] = Integer.min(minL[i - 1], points[i].y);
        }

        maxR[N - 1] = points[N - 1].y;
        for (int i = N - 2; i >= 0; i--) {
            maxR[i] = Integer.max(maxR[i + 1], points[i].y);
        }

        int ans = 1;
        for (int i = 0; i < N - 1; i++) {
            //top left
            if (minL[i] > maxR[i + 1]) {
                ans++;
            }
        }

        pw.println(ans);
        pw.close();


    }
}

class point implements Comparable<point> {
    public int x;
    public int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(point other) {
        if (this.x != other.x) {
            return this.x - other.x;
        }
        return this.y - other.y;

    }


}
