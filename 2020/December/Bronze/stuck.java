import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class stuck {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        point[] points = new point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char dir = st.nextToken().charAt(0);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new point(x, y, dir);
        }


        List<Integer> times = new ArrayList<>();
        //Meeting times.
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                times.add(Math.abs(points[j].x - points[i].x));
                times.add(Math.abs(points[j].y - points[i].y));
            }
        }
        int[] ret = new int[N];
        Arrays.fill(ret, Integer.MAX_VALUE);
        Collections.sort(times);
        for (int d : times) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (points[j].dir == 'E' && points[k].dir == 'N' && points[j].x < points[k].x && points[k].y < points[j].y) {
                        if (points[j].x + d == points[k].x && points[k].y + Math.min(ret[k], d) > points[j].y) {
                            ret[j] = Math.min(ret[j], d);
                        } else if (points[k].y + d == points[j].y && points[j].x + Math.min(ret[j], d) > points[k].x) {
                            ret[k] = Math.min(ret[k], d);
                        }
                    }
                }
            }
        }
        for (int i : ret) {
            if (i == Integer.MAX_VALUE) {
                System.out.println("Infinity");

            } else {
                System.out.println(i);
            }


        }


    }

    static class point {
        int x, y;
        char dir;

        public point(int x, int y, char dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

}
