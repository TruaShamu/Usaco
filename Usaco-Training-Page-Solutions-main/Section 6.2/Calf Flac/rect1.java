import java.io.*;
import java.util.StringTokenizer;

/*
TASK: rect1
LANG: JAVA
 */
public class rect1 {
    public static rectangle[] co = new rectangle[1005];
    public static int[] count = new int[2501];
    public static int w, l, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rect1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < co.length; i++) {
            co[i] = new rectangle(0, 0, 0, 0);
        }
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        co[0].x2 = w;
        co[0].y2 = l;
        co[0].c = 1;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            co[i].x1 = Integer.parseInt(st.nextToken());
            co[i].y1 = Integer.parseInt(st.nextToken());
            co[i].x2 = Integer.parseInt(st.nextToken());
            co[i].y2 = Integer.parseInt(st.nextToken());
            co[i].c = Integer.parseInt(st.nextToken());
        }

        for (int cs = n; cs >= 0; cs--) {
            count[co[cs].c] += slice(co[cs], cs, n);
        }
        for (int i = 1; i <= 2500; i++) {
            if (count[i] != 0) {
                System.out.println(i + " " + count[i]);
                pw.println(i + " " + count[i]);
            }
        }
        pw.close();
    }

    public static int slice(rectangle a, int i, int n) {
        int ar = (a.x2 - a.x1) * (a.y2 - a.y1);
        if (ar <= 0 || a.x2 < a.x1 || a.y2 < a.y1) {
            return 0;
        }
        if (n == i) {
            return ar;
        }
        rectangle b = co[n];
        if (b.x1 > a.x2 || b.x2 < a.x1 || b.y1 > a.y2 || b.y2 < a.y1) {
            return slice(a, i, n - 1);
        }
        int t = slice(new rectangle(a.x1, a.y1, b.x1, a.y2), i, n - 1);
        t += slice(new rectangle(b.x2, a.y1, a.x2, a.y2), i, n - 1);
        t += slice(new rectangle(Integer.max(a.x1, b.x1), b.y2, Integer.min(a.x2, b.x2), a.y2), i, n - 1);
        t += slice(new rectangle(Integer.max(a.x1, b.x1), a.y1, Integer.min(a.x2, b.x2), b.y1), i, n - 1);
        return t;
    }


}

class rectangle {
    int x1, y1, x2, y2, c;

    public rectangle(int i1, int i2, int i3, int i4) {
        x1 = i1;
        y1 = i2;
        x2 = i3;
        y2 = i4;
        c = 0;
    }


}

