import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class painting {
    public static rect[] points;
    public static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("painting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("painting.out")));
        number = Integer.parseInt(br.readLine());
        points = new rect[number];//number down xy topx topy right
        boolean[] lap = new boolean[number];

        for (int x = 0; x < number; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            points[x] = new rect(x1, y1, x2, y2);
        }

        Arrays.sort(points);


        for (int y = 0; y < number; y++) {
            if (lap[y]) {
                continue;
            }
            int X = points[y].x1;
            int Y = points[y].y1;
            int max = points[y].c;
            int maxY = points[y].d;

            for (int x = y + 1; x < number; x++) {
                if (points[x].c <= X) {
                    continue; //end before you start
                }
                if (points[x].x1 >= max) {
                    break;//start after you end
                }
                if (X <= points[x].x1 && max >= points[x].c && Y <= points[x].y1 && maxY >= points[x].d) {
                    lap[x] = true;

                } else if (X >= points[x].x1 && max <= points[x].c && Y >= points[x].y1 && maxY <= points[x].d) {
                    lap[y] = true;
                    break;
                }
            }
        }

        int count = 0;
        for (int x = 0; x < number; x++) {
            if (!lap[x]) {
                count++;
            }
        }


        pw.println(count);
        pw.close();
    }


}

class rect implements Comparable<rect> {
    int x1;
    int y1;
    int c;
    int d;

    public rect(int x, int y, int z, int w) {
        x1 = x;
        y1 = y;
        c = z;
        d = w;
    }

    public int compareTo(rect oRect) {
        return Integer.compare(this.x1, oRect.x1);
    }
}
