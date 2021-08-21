/*
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.Arrays;

public class transform {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("transform.in"));
        PrintWriter pw = new PrintWriter(new File("transform.out"));
        N = Integer.parseInt(br.readLine());

        char[][] before = new char[N][N];
        for (int i = 0; i < N; i++) {
            before[i] = br.readLine().toCharArray();
        }

        char[][] after = new char[N][N];
        for (int i = 0; i < N; i++) {
            after[i] = br.readLine().toCharArray();
        }


        //@todo Check rotate
        char[][] trans = CW(before);
        for (int i = 1; i < 3; i++) {
            if (Arrays.deepEquals(after, trans)) {
                pw.println(i);
                pw.close();
                return;
            }
            trans = CW(trans);
        }

        char[][] reflected = reflect(before);
        trans = CW(reflected);
        if (Arrays.deepEquals(after, reflected)) {
            pw.println(4);
            pw.close();
            System.exit(0);
        }
        for (int i = 1; i < 3; i++) {
            if (Arrays.deepEquals(after, trans)) {
                pw.println(5);
                pw.close();
                return;
            }
            trans = CW(trans);
        }
//@todo Identical.
        if (Arrays.deepEquals(after, before)) {
            pw.println(6);
            pw.close();
            return;
        }
        pw.println(7);
        pw.close();


    }


    public static char[][] CW(char[][] arr) {
        char[][] ret = new char[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][N - r - 1] = arr[r][c];

            }
        }
        return ret;
    }

    public static char[][] reflect(char[][] arr) {
        char[][] ret = new char[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < arr.length; c++) {
                ret[r][c] = arr[r][N - c - 1];
            }
        }
        return ret;
    }


}
