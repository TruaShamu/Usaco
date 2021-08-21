import java.io.*;
import java.util.StringTokenizer;

/*
 LANG: JAVA
 PROG: fence9
  */
public class fence9 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence9.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        double area = 0.5 * (y * p);
        if (y == 0) {
            pw.println(0);
            pw.close();
            System.exit(0);
        }
        long triangleArea = p * y / 2;
        long answer = triangleArea + 1 - (gcd(x, y) + gcd(Math.abs(x - p), y) + p) / 2;
        pw.println(answer);


        pw.close();
    }

    public static int gcd(int a, int b) {
        int t;
        if (a == 0) return b;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
