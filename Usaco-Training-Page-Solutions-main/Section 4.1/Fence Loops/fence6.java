import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: fence6
LANG: JAVA
 */
public class fence6 {
    public static int N;
    public static int FILLER = 99999999;
    public static segment[] segArr = new segment[101]; //Array of the segments
    public static int table[][] = new int[201][201]; //Length of segments from a to b

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fence6.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
        N = Integer.parseInt(br.readLine());
        long start = System.currentTimeMillis();
        for (int i = 1; i <= N; i++) {
            //Read input
            StringTokenizer st = new StringTokenizer(br.readLine());
            int segNum = Integer.parseInt(st.nextToken());
            int segLen = Integer.parseInt(st.nextToken());
            int line2 = Integer.parseInt(st.nextToken());
            int line3 = Integer.parseInt(st.nextToken());
            segArr[segNum] = new segment(segNum, segLen);
            //Line2
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < line2; j++) {
                segArr[segNum].addLeft(Integer.parseInt(st.nextToken()));
            }
            //Line3
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < line3; j++) {
                segArr[segNum].addRight(Integer.parseInt(st.nextToken()));
            }
        }
        //Finish reading input
        for (int i = 0; i < 201; i++) {
            Arrays.fill(table[i], FILLER);
        }
        segArr[1].lNode = 1;
        segArr[1].rNode = 2;
        int index = 3;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < segArr[i].lConnect.length; j++) {
                if (segArr[i].lConnect[j]) {
                    if (segArr[j].lConnect[i]) {
                        segArr[j].lNode = segArr[i].lNode;
                    }
                    if (segArr[j].rConnect[i]) {
                        segArr[j].rNode = segArr[i].lNode;
                    }
                    if (segArr[j].lNode == 0) {
                        segArr[j].lNode = index++;
                    }
                    if (segArr[j].rNode == 0) {
                        segArr[j].rNode = index++;
                    }
                }
            }
            for (int j = 0; j < segArr[i].rConnect.length; j++) {
                if (segArr[i].rConnect[j]) {
                    if (segArr[j].lConnect[i]) {
                        segArr[j].lNode = segArr[i].rNode;
                    }
                    if (segArr[j].rConnect[i]) {
                        segArr[j].rNode = segArr[i].rNode;
                    }
                    if (segArr[j].lNode == 0) {
                        segArr[j].lNode = index++;
                    }
                    if (segArr[j].rNode == 0) {
                        segArr[j].rNode = index++;
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            table[segArr[i].lNode][segArr[i].rNode] = segArr[i].length;
            table[segArr[i].rNode][segArr[i].lNode] = segArr[i].length;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (table[i][j] != 0) {
                    int tmp = table[i][j];
                    table[i][j] = 99999999;
                    int s = djikstra(i, index, j);
                    if (s < FILLER) {
                        if (tmp + s < result) {
                            result = tmp + s;
                            System.out.println(j + " " + s);
                        }
                    }
                    table[i][j] = tmp;
                }
            }
        }
        pw.println(result);
        pw.close();

        long time = System.currentTimeMillis() - start;


    }

    public static int djikstra(int s, int index, int t) {
        int d[] = new int[index];
        boolean vis[] = new boolean[index];
        Arrays.fill(d, 99999999);
        d[s] = 0;
        while (true) {
            int v = -1;
            for (int u = 1; u < index; u++) {
                if (!vis[u] && (v == -1 || d[u] < d[v])) v = u;
            }
            if (v == -1) break;
            vis[v] = true;
            for (int u = 1; u < index; u++) {
                d[u] = Math.min(d[u], d[v] + table[v][u]);
            }
        }
        return d[t];
    }

}

class segment {
    public int id, length;
    public boolean lConnect[] = new boolean[201]; //Can it connect to these segments on each side
    public boolean rConnect[] = new boolean[201];
    public int lNode = 0;
    public int rNode = 0;

    public segment(int id, int length) {
        this.length = length;
        this.id = id;
    }

    public void addLeft(int oSeg) {
        lConnect[oSeg] = true;
    }

    public void addRight(int oSeg) {
        rConnect[oSeg] = true;
    }

    public void tosString() {
        System.out.println(lNode + " " + rNode + " " + length);
    }


}
