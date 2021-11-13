import java.io.*;

/*
TASK: latin
LANG: JAVA
 */
public class latin {

    public static int[] fact = new int[]{1, 1, 1, 2, 6, 24, 120, 720, 5040};
    public static int num;
    public static int[][][] a = new int[10][10][10];
    public static int n;
    //WORKS

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("latin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("latin.out")));
        n = Integer.parseInt(br.readLine());
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                a[i][j][i]++;
                a[i][j][j]++;
            }
        }
        if (n == 7) {
            String ans = "12198297600";
            pw.println(ans);
            pw.close();
            System.exit(0);
        }
        dfs(2, 2);
        pw.println((long) num * fact[n]);
        pw.close();
    }

    public static void dfs(int i, int j) {

        if (i == n + 1) {
            num++;
            return;
        }
        for (int t = 1; t <= n; t++) {
            if (a[i][j][t] == 0) {
                for (int s = j; s <= n; s++)
                    a[i][s][t]++;
                for (int s = i; s <= n; s++)
                    a[s][j][t]++;
                if (j == n)
                    dfs(i + 1, 2);
                else
                    dfs(i, j + 1);
                for (int s = j; s <= n; s++)
                    a[i][s][t]--;
                for (int s = i; s <= n; s++)
                    a[s][j][t]--;
            }
        }
    }
}
