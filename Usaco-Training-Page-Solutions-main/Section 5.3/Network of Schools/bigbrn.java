import java.io.*;
import java.util.StringTokenizer;

/*
TASK: bigbrn
ID: truashamu
LANG: JAVA
 */
public class bigbrn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bigbrn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int trees = Integer.parseInt(st.nextToken());
        int[][] ans = new int[rows][rows];
        boolean[][] barrier = new boolean[rows][rows];
        for (int i = 0; i < trees; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int column = Integer.parseInt(st.nextToken());
            barrier[row - 1][column - 1] = true;
        }
        //Initialize dp
        for (int i = 0; i < rows; i++) {
            if (barrier[i][0]) {
                ans[i][0] = 0;
            } else {
                ans[i][0] = 1;
            }
            if (barrier[0][i]) {
                ans[0][i] = 0;
            } else {
                ans[0][i] = 1;
            }
        }
        int ret = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (i > 0 && j > 0) {
                    int m;
                    if (barrier[i][j]) {
                        m = 0;
                    } else {
                        m = Math.min(ans[i - 1][j], ans[i][j - 1]);
                    }
                    int add;
                    if (m == 0 || ans[i - m][j - m] > 0) {
                        add = 1;
                    } else {
                        add = 0;
                    }
                    if (barrier[i][j]) {
                        ans[i][j] = m;
                    } else {
                        ans[i][j] = m + add;
                    }
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < rows; column++) {
                ret = Integer.max(ans[row][column], ret);
            }
        }
        System.out.println("RETURN: " + ret);
        pw.println(ret);
        pw.close();

    }
}
//trua trua trua
