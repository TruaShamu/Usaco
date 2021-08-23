import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
TASK: twofive
LANG: JAVA
 */
public class twofive {
    public static int[] maxRow = new int[6];
    public static int[] maxCol = new int[6];
    public static int[] col = new int[6];
    public static boolean[] used = new boolean[60];
    public static int[][][][][] dp = new int[6][6][6][6][6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("twofive.in"));
        PrintWriter pw = new PrintWriter("twofive.out");
        char type = br.readLine().charAt(0);
        if (type == 'N') {
            int n = Integer.parseInt(br.readLine());
            Arrays.fill(maxCol, -1);
            Arrays.fill(maxRow, -1);
            int ans = 0;
            for (int i = 0; i < 25; i++) {
                col[i / 5]++;
                int j;
                for (j = 0; j < 25; j++) {
                    if (!used[j] && j > maxCol[i % 5] && j > maxRow[i / 5]) {
                        place(i / 5, i % 5, j);
                        int tmp = dfs(col[0], col[1], col[2], col[3], col[4], 0);
                        if (ans + tmp >= n) break;
                        ans += tmp;
                        used[j] = false;
                    }
                }
                pw.print((char) (j + 'A'));
            }
            pw.println();
            pw.close();
        }
        if (type == 'W') {
            String s = br.readLine();
            int ans = 0;
            for (int i = 0; i < 25; i++) {
                col[i / 5]++;
                int tmp;
                for (tmp = 0; tmp < (s.charAt(i) - 'A'); tmp++) {
                    if (!used[tmp] && tmp > maxCol[i % 5] && tmp > maxRow[i / 5]) {
                        place(i / 5, i % 5, tmp);
                        ans += dfs(col[0], col[1], col[2], col[3], col[4], 0);
                        used[tmp] = false;
                    }
                }
                used[tmp] = true;
                maxRow[i / 5] = maxCol[i % 5] = tmp;
            }
            pw.println(ans + 1);
            pw.close();
        }
    }

    public static int dfs(int a, int b, int c, int d, int e, int ch) {
        int tmp = dp[a][b][c][d][e];
        if (tmp != 0) return tmp;
        if (used[ch]) return dfs(a, b, c, d, e, ch + 1);
        if (a < 5 && ch > maxRow[0] && ch > maxCol[a])
            tmp += dfs(a + 1, b, c, d, e, ch + 1);
        if (b < a && ch > maxRow[1] && ch > maxCol[b])
            tmp += dfs(a, b + 1, c, d, e, ch + 1);
        if (c < b && ch > maxRow[2] && ch > maxCol[c])
            tmp += dfs(a, b, c + 1, d, e, ch + 1);
        if (d < c && ch > maxRow[3] && ch > maxCol[d])
            tmp += dfs(a, b, c, d + 1, e, ch + 1);
        if (e < d && ch > maxRow[4] && ch > maxCol[e])
            tmp += dfs(a, b, c, d, e + 1, ch + 1);
        dp[a][b][c][d][e] = tmp;
        return tmp;
    }

    public static void place(int i, int j, int ch) {
        fill();
        dp[5][5][5][5][5] = 1;
        maxRow[i] = maxCol[j] = ch;
        used[ch] = true;
    }

    public static void fill() {
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 6; b++) {
                for (int c = 0; c < 6; c++) {
                    for (int d = 0; d < 6; d++) {
                        for (int e = 0; e < 6; e++) {
                            dp[a][b][c][d][e] = 0;
                        }
                    }
                }
            }
        }
    }
}
