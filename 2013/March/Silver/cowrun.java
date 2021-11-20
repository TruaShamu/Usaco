import java.util.*;
import java.io.*;


public class Main {

    static long[][][] dp;//down is negetive across is positive
    static int[] line;
    static int start;
    static int left;
    static int right;
    static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowrun.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));

        number = Integer.parseInt(br.readLine());
        line = new int[number + 1];
        for (int x = 0; x < number; x++) {
            line[x] = Integer.parseInt(br.readLine());
        }
        line[number] = 0;
        Arrays.sort(line);
        for (int x = 0; x < line.length; x++) {
            if (line[x] == 0) {
                start = x;
                break;
            }
        }
        left = start;
        right = number - start;

        dp = new long[left + 1][right + 1][2];

        for (int y = 0; y <= left; y++) {
            for (int x = 0; x <= right; x++) {
                dp[y][x][0] = -1;
                dp[y][x][1] = -1;

            }
        }
        dp[left][right][0] = 0;
        dp[left][right][1] = 0;
        pw.println(find(0, 0, 0));
        print();


        pw.close();
        System.exit(0);
    }

    static void print() {
        for (int y = 0; y <= left; y++) {
            for (int x = 0; x <= right; x++) {
                System.out.print(dp[y][x][0] + " ");
            }
            System.out.println();
        }
        for (int y = 0; y <= left; y++) {
            for (int x = 0; x <= right; x++) {
                System.out.print(dp[y][x][1] + " ");
            }
            System.out.println();
        }
    }


    static long find(int a, int b, int cur) {
        if (dp[a][b][cur] != -1) {
            return dp[a][b][cur];
        }
        if (a > left || b > right) {
            return Long.MAX_VALUE;//if out of bounds, give large answer back
        }
        int loc;
        if (cur == 0) {
            loc = -a;
        } else loc = b;
        long l, r;
        if (a < left) {
            l = Math.abs(line[start + loc] - line[start - a - 1]) * (number - a - b) + find(a + 1, b, 0);
        } else {
            l = Long.MAX_VALUE;
        }
        if (b < right) {
            r = Math.abs(line[start + b + 1] - line[start + loc]) * (number - a - b) + find(a, b + 1, 1);
        } else {
            r = Long.MAX_VALUE;
        }
        //System.out.println(a+" "+ b+" "+l+" "+r);
        dp[a][b][cur] = Math.min(l, r);

        return dp[a][b][cur];

    }
}
