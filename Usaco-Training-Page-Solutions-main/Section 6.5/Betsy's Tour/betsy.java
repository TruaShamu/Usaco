import java.io.*;

/*
LANG: JAVA
TASK: betsy
 */
public class betsy {
    public static int N;
    public static boolean[][] visited = new boolean[10][10];
    public static int[] di = {0, 0, 1, -1};
    public static int[] dj = {1, -1, 0, 0};
    public static char[][] area = new char[10][10];
    public static int total, visit, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("betsy.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("betsy.out")));
        N = Integer.parseInt(br.readLine());
        Initialize();
        DFS(1, 1);
        pw.println(cnt);
        pw.close();

    }

    public static void DFS(int x, int y) {
        int special;
        if (area[x - 1][y] == 1 && area[x + 1][y] == 1 && area[x][y - 1] == 0 && area[x][y + 1] == 0 || area[x - 1][y] == 0 && area[x + 1][y] == 0 && area[x][y - 1] == 1 && area[x][y + 1] == 1)
            return;
        area[x][y] = 1;
        visit++;
        if (x == N && y == 1) {
            if (visit == total) {
                cnt++;
            }
        } else {
            special = isspecial(x - 1, y) + isspecial(x + 1, y) + isspecial(x, y - 1) + isspecial(x, y + 1);
            if (special == 0) {
                if (area[x - 1][y] == 0) {
                    DFS(x - 1, y);
                }
                if (area[x][y + 1] == 0) {
                    DFS(x, y + 1);
                }
                if (area[x + 1][y] == 0) {
                    DFS(x + 1, y);
                }
                if (area[x][y - 1] == 0) {
                    DFS(x, y - 1);
                }
            } else if (special == 1) {
                if (isspecial(x - 1, y) > 0) {
                    DFS(x - 1, y);
                }
                if (isspecial(x, y + 1) > 0) {
                    DFS(x, y + 1);
                }
                if (isspecial(x + 1, y) > 0) {
                    DFS(x + 1, y);
                }
                if (isspecial(x, y - 1) > 0) {
                    DFS(x, y - 1);
                }
            }
        }
        visit--;
        area[x][y] = 0;
    }

    public static void Initialize() {
        int i;
        for (i = 0; i <= N + 1; i++) {
            area[0][i] = area[i][0] = area[N + 1][i] = area[i][N + 1] = 1;
        }
        total = N * N;
    }

    public static int isspecial(int x, int y) {
        boolean b = (!(x == N && y == 1)) && (area[x][y] == 0) && (area[x - 1][y] + area[x][y - 1] + area[x + 1][y] + area[x][y + 1] == 3);
        int val = (b) ? 1 : 0;
        return val;
    }

}
