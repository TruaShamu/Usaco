import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
     SCORE: 10/10
    DATE: 2/9/20
     */
    public static int a, b, c, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paint.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        if (checkOverlap()) {
            pw.println(Integer.max(b, d) - Integer.min(a, c));
        } else {
            pw.println((b - a) + (d - c));
        }
        pw.close();

    }

    public static boolean checkOverlap() {
        if (a <= c && c <= b) {
            return true;
        }
        if (c <= a && a <= d) {
            return true;
        }
        return false;
    }
}
