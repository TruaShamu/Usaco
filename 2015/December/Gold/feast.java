import java.io.*;
import java.util.StringTokenizer;

public class feast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("feast.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        boolean[][] seen = new boolean[2][t + 1];
        seen[0][0] = true;
        for (int a = 0; a < seen.length; a++) {
            for (int i = 0; i < seen[a].length; i++) {
                if (!seen[a][i]) {
                    continue;
                }
                if (i + x <= t) {
                    seen[a][i + x] = true;
                }
                if (i + y <= t) {
                    seen[a][i + y] = true;
                }
                if (a + 1 < seen.length) {
                    seen[a + 1][i / 2] = true;
                }
            }
        }
        int ret = t;
        while (!seen[1][ret]) {
            ret--;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        pw.println(ret);
        pw.close();
    }
}
