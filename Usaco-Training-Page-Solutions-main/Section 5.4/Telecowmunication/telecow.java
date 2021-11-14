import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: telecow
LANG: JAVA
 */
public class telecow {
    public static int[][] map = new int[128][128];
    public static int[][] maptmp = new int[128][128];
    public static int[] left = new int[128];
    public static int[] visit = new int[128];
    public static int[] queue = new int[128];
    public static int[] pre = new int[128];
    public static int[] getout = new int[128];
    public static int source, sink, nodes;
    public static int cnt = 0, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("telecow.in"));
        PrintWriter pw = new PrintWriter("telecow.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        source = Integer.parseInt(st.nextToken());
        sink = Integer.parseInt(st.nextToken());
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int otherNode = Integer.parseInt(st.nextToken());
            map[node][otherNode] = map[otherNode][node] = maptmp[otherNode][node] = maptmp[node][otherNode] = 1;
        }
        Arrays.fill(left, -1);
        while (Exist() > 0) {
            cnt++;
        }
        pw.println(cnt);
        for (int i = 1; i <= nodes && cnt != 0; i++) {
            if (i == source || i == sink)
                continue;
            tmp = 0;
            for (int j = 1; j <= nodes; j++)
                maptmp[i][j] = maptmp[j][i] = 0;
            Arrays.fill(left, -1);
            while (Exist() > 0) {
                tmp++;
            }
            if (tmp != cnt) {
                cnt--;
                if (cnt > 0)
                    pw.print(i + " ");
                else
                    pw.println(i);
                getout[i] = 1;
            } else {
                for (int j = 1; j <= nodes; j++) {
                    if (getout[j] == 1)
                        continue;
                    maptmp[i][j] = maptmp[j][i] = map[i][j];
                }
            }
        }
        //pw.println();
        pw.close();


    }

    public static int Exist() {
        int tmp, h, r, i;
        Arrays.fill(visit, 0);
        tmp = h = r = 0;
        queue[r++] = source;
        while (h != r) {
            tmp = queue[h++];
            if (tmp == sink)
                break;
            for (i = 1; i <= nodes; i++) {
                if (visit[i] == 0 && left[i] != 0 && maptmp[tmp][i] == 1) {
                    queue[r++] = i;
                    pre[i] = tmp;
                    visit[i] = 1;
                }
            }
        }
        if (tmp == sink) {
            i = pre[sink];
            while (i != source) {
                left[i] = 0;
                i = pre[i];
            }
            return 1;
        }
        return 0;
    }


}
